# Automatic deployment pipelines/workflows
GitHub Actions has been chosen for the CI/CD pipelines due to it's simplicity and seamless integration to GitHub repositories. This document explains configuration and different steps that are executed in automatic deployments.

# Diagram

![](GitHub%20Actions.drawio.png)

# Structure

In this folder, two .yml files can be found - backend and frontend. The files contain GitHub actions workflows that are used to build and deploy applications respectively.

# Steps
The main steps of the workflow are as follows: 
1. Check for change in GitHub repository
2. Pull the code
3. AWS and Docker cli setup
4. Build the docker image
5. Push the image to remote repository
6. Trigger the update on elastic beanstalk


## 1. Check for changes in repository
```
on:
  push:
    branches:
      - main
    paths:
      - '<backend/frontend>/**'
  workflow_dispatch:
```
According to [GitHub workflow trigger documentation](https://docs.github.com/en/actions/using-workflows/events-that-trigger-workflows), 
there are multiple ways how can the workflow can be triggered. It was chosen to use main two triggers: On `push` and on `workflow_dispatch`. 
The on push trigger checks if the latest commit has made a change in main branch, and in a specific subdirectory.
Because our git repository is a monorepo (contains multiple projects), we want to trigger the workflow for the application where the change has been made. 
This is done to ensure that the workflows are triggered only when necessary (e.g. when change happens in app).
The workflow_dispatch is used so that it is possible to trigger the workflow via GitHub website.

## 2. Pull the code
```
- uses: actions/checkout@v3
```
This [reusable GitHub Action](https://github.com/actions/checkout) is responsible for pulling the code from GitHub repository to the machine/runner that is running the GitHub workflow. The code will be later used in other steps.

## 3. AWS and Docker cli setup
```
- name: Configure AWS credentials
  uses: aws-actions/configure-aws-credentials@v1
  with:
      aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
      aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
      aws-region: eu-central-1
- name: Login to Amazon ECR
  id: login-ecr
  uses: aws-actions/amazon-ecr-login@v1
```
Another [GitHub action](https://github.com/aws-actions/configure-aws-credentials) that has been used for setting the AWS cli. The sensitive data related to AWS access keys has been saved in GitHub repository as repository secret, 
and can be used in the workflow with `${{ secrets.<SECRET_NAME> }}` notation. 
The [amazon-ecr-login action](https://github.com/aws-actions/amazon-ecr-login) is responsible for configuring docker cli with AWS ECR, so that docker can push the images to ECR repository </br>
NOTE: The creation of AWS IAM user with correct permissions is a prerequisite.

## 4. Build the docker image
```
- name: Build, tag, and push image to Amazon ECR
  working-directory: <frontend/backend>
  run: |
    FULL_REPO_PATH=${{ steps.login-ecr.outputs.registry }}/${{ env.ECR_REPOSITORY }}
    docker build -t $FULL_REPO_PATH .
```
This step is used to create a docker image. The `working-directory` keyword, let's us specify the subfolder of git repository where the commands are supposed to be executed.
The repo of the image is constructed from different environment variables and then used to mark the built image with a `-t` parameter.

## 5. Push the image to remote repository
```
docker push $FULL_REPO_PATH          

REPO_PATH_WITH_RUN_NUMBER=$FULL_REPO_PATH:${{ env.IMAGE_TAG }}
docker tag $FULL_REPO_PATH $REPO_PATH_WITH_RUN_NUMBER
docker push $REPO_PATH_WITH_RUN_NUMBER
```
The marked built image is then pushed to remote docker image repository depending on the mark of the image. 
Because we did not specify the tag (version) of the image, the image gets pushed to remote as `latest` image. 
In order to also have an information about the workflow run that has created the image in ECR, we tag the image with the run number, and we push again.

## 6. Trigger the update on elastic beanstalk

```
- name: Redeploy elastic beanstalk to use the image with the latest tag
  run: aws elasticbeanstalk update-environment --application-name "sep6-server" --environment-name "sep6-server" --version-label=1
```
In order to update the docker image running in the cloud, `aws elasticbeanstalk update-environment` is executed. This command needs three arguments. 
The `--application-name` and `--sep6-server` are used to point to the elastic beanstalk setup to be updated,
and `--version-label` contains the version of the app to be updated. In our case, the version label points to 1 that contains `docker-compose.yml` file. This file is then used by docker to get information about to images to pull and their respective port mappings. 
The images are then pulled and redeployed to the server running the appications. </br>
NOTE: The docker-compose.yml in the version 1 is a prerequisite for this command to work. After it has been created, there is no need for changes anymore.
