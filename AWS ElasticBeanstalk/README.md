This docker compose file contains configuration for the applications that needs to be run in the AWS Elastic Beanstalk instance. It contains two services each representing the two applications that are in this repository: 

* Frontend
* Backend

Each service consist of docker image url and port mapping that is used to map container to the port of the host/vm (in our case EC2 instance). When a new version needs to be deployed, we need to call cli command `aws elasticbeanstalk update-environment` that refreshes the docker-compose.yml inside the hosting server/vm. This way the docker daemon pulls and run the docker image with the `:latest` tag.  