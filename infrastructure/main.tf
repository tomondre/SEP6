resource "aws_elastic_beanstalk_application" "sep6-eb-application" {
  name = "sep6-server"
}

resource "aws_elastic_beanstalk_environment" "sep6-eb-environment" {
  application         = "sep6-server"
  cname_prefix        = "sep6-server"
  name                = "sep6-server"
  solution_stack_name = "64bit Amazon Linux 2 v3.5.7 running Docker"
  tier                = "WebServer"
  version_label       = "1"

  setting {
    name      = "AWSEBEC2LaunchTemplate"
    namespace = "aws:autoscaling:launchconfiguration"
    resource = "EC2KeyName"
    value     = "sep6-key"
  }

  setting {
    name      = "InstanceType"
    namespace = "aws:autoscaling:launchconfiguration"
    value     = "t2.micro"
  }

  setting {
    name      = "aws:elasticbeanstalk:environment"
    namespace = "aws:elasticbeanstalk:environment"
    resource = "ServiceRole"
    value     = "arn:aws:iam::849514279839:role/service-role/aws-elasticbeanstalk-service-role"
  }

  setting {
    namespace = "aws:rds:dbinstance"
    name      = "DBEngine"
    value     = "mysql"
    resource  = "AWSEBRDSDatabase"
  }

  setting {
    namespace = "aws:rds:dbinstance"
    name      = "DBInstanceClass"
    value     = "db.t2.micro"
    resource  = "AWSEBRDSDatabase"
  }

  setting {
    name      = "DBUser"
    namespace = "aws:rds:dbinstance"
    value     = "sep6user"
  }

  setting {
    name      = "DBPassword"
    namespace = "aws:rds:dbinstance"
    value     = "****"
  }

  setting {
    name      = "EnvironmentType"
    namespace = "aws:elasticbeanstalk:environment"
    value     = "LoadBalanced"
  }

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MaxSize"
    value     = "2"
    resource  = "AWSEBAutoScalingGroup"
  }

  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "1"
    resource  = "AWSEBAutoScalingGroup"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "EC2KeyName"
    value     = "sep6-key"
    resource  = "AWSEBEC2LaunchTemplate"
  }

  setting {
    name      = "DeploymentPolicy"
    namespace = "aws:elasticbeanstalk:command"
    value     = "RollingWithAdditionalBatch"
  }
  setting {
    namespace = "aws:elbv2:listener:default"
    name      = "DefaultProcess"
    value     = "default"
  }

  setting {
    namespace = "aws:elbv2:listener:default"
    name      = "ListenerEnabled"
    value     = "true"
  }

  setting {
    namespace = "aws:elbv2:listener:default"
    name      = "Protocol"
    value     = "HTTP"
  }

  setting {
    namespace = "aws:elbv2:listener:default"
    name      = "Rules"
    value     = "backend,frontend"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:backend"
    name      = "HostHeaders"
    value     = ""
  }

  setting {
    namespace = "aws:elbv2:listenerrule:backend"
    name      = "PathPatterns"
    value     = "/backend/*"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:backend"
    name      = "Priority"
    value     = "2"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:backend"
    name      = "Process"
    value     = "default"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:frontend"
    name      = "HostHeaders"
    value     = ":default"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:frontend"
    name      = "PathPatterns"
    value     = "/frontend/*"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:frontend"
    name      = "Priority"
    value     = "1"
  }

  setting {
    namespace = "aws:elbv2:listenerrule:frontend"
    name      = "Process"
    value     = "frontend"
  }

  setting {
    namespace = "aws:elasticbeanstalk:environment:process:default"
    name      = "Port"
    value     = "3000"
    resource  = "AWSEBV2LoadBalancerTargetGroup"
  }

  setting {
    namespace = "aws:elasticbeanstalk:environment:process:frontend"
    name      = "Port"
    value     = "8080"
    resource  = "frontend"
  }
}
