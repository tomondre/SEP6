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
  cpu_request = "500m"
  cpu_limit = "1000m"
  env               = {
    DB_HOST         = var.db_host
    DB_USERNAME     = var.db_username
    DB_PASSWORD     = var.db_password
    JWT_SECRET_KEY  = var.jwt_secret_key
  }
}

variable "image_tag" {}
variable "db_host" {}
variable "db_username" {}
variable "db_password" {}
variable "jwt_secret_key" {}
