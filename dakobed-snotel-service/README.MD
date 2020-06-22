#Dakobed Snotel Service


aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 710339184759.dkr.ecr.us-west-2.amazonaws.com

docker build -t dakobed-snotel-service .

docker tag dakobed-snotel-service:latest 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-snotel-service:latest

docker push 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-snotel-service:latest