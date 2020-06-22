#Dakobed Vue Frontend

#### ECR 

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 710339184759.dkr.ecr.us-west-2.amazonaws.com

docker build -t dakobed-vue-repository .
docker run -it -p 8080:8080 --rm --name dockerize-vuejs-app-1 dakobed/dakobed-vuejs-app

docker tag dakobed-vue-repository:latest 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-vue-repository:latest

docker push 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-vue-repository:latest