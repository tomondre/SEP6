terraform {
  cloud {
    organization = "raspberry-kubernetes-cluster"

    workspaces {
      name = "tomondre-sep6-frontend"
    }
  }
}

module "deployments_sep6_frontend" {
  source            = "git::https://github.com/tomondre/raspberry-kubernetes-cluster.git//terraform-modules/reusable-modules/full-deployment"
  health_check_path = "/health.json"
  image_url         = "docker.io/tomondre/sep6-frontend"
  service_name      = "sep6-frontend"
  port              = 8080
  host_name         = "sep6"
  image_tag         = var.image_tag
}

variable "image_tag" {}
