terraform {
  cloud {
    organization = "raspberry-kubernetes-cluster"

    workspaces {
      name = "tomondre-sep6-backend"
    }
  }
}

module "deployments_sep6_backend" {
  source            = "git::https://github.com/tomondre/raspberry-kubernetes-cluster.git//terraform-modules/reusable-modules/full-deployment"
  health_check_path = "/actuator/health"
  image_url         = "docker.io/tomondre/sep6-backend"
  service_name      = "sep6-backend"
  port              = 8000
  host_name         = "sep6-api"
  image_tag         = var.image_tag
}

variable "image_tag" {}
