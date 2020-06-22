#Dakobed Stack

Utilize Cloudformation to provision resources


aws cloudformation create-stack --stack-name DakobedStack --capabilities CAPABILITY_NAMED_IAM \
--template-body file://core.yml   

aws cloudformation describe-stacks --stack-name DakobedStack

To save the IDS of resources by cloud formation to a file 

aws cloudformation describe-stacks --stack-name DakobedStack > ./core-output.json


##### Create ECS Cluster

aws ecs create-cluster --cluster-name Dakobed-Fargate-Cluster

###### Dakobed Logs
aws logs create-log-group --log-group-name dakobed-logs

##### Register a task
aws ecs register-task-definition --cli-input-json file://snotel-task-definition.json


##### Create Network Load Balancer
aws elbv2 create-load-balancer --name dakobed-network-load-balancer --scheme internet-facing --type network --subnets subnet-0ec15e101c2157a2c subnet-0abd5728cc4e12536 > ~/nlb-output.json


#### Create Application Load Balancer
aws elbv2 create-load-balancer --name dakobed-application-lb --scheme internet-facing --type application --subnets subnet-0ec15e101c2157a2c subnet-0abd5728cc4e12536 > ./alb-output.json


arn:aws:elasticloadbalancing:us-west-2:710339184759:loadbalancer/app/dakobed-application-lb/3ae376999a3a0106

##### Create a Load Balancer Target Group




aws elbv2 create-target-group --name Dakobed-TargetGroup --port 8080 --protocol TCP --target-type ip --vpc-id \
 vpc-08cf10763c590a599 --health-check-interval-seconds 10 --health-check-path / --health-check-protocol HTTP --healthy-threshold-count 3 --unhealthy-threshold-count 3 > opn

##### Create a load balancer listener
aws elbv2 create-listener --default-actions TargetGroupArn=arn:aws:elasticloadbalancing:us-west-2:710339184759:targetgroup/Dakobed-TargetGroup/10b19a00b9fba548,Type=forward \
 --load-balancer-arn arn:aws:elasticloadbalancing:us-west-2:710339184759:loadbalancer/net/dakobed-network-load-balancer/872f9a061664a660 --port 80 --protocol TCP
 
##### Create Service
aws ecs create-service --cli-input-json file://snotel-service-definition.json
