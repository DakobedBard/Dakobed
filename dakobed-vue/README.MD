#Dakobed Vue Frontend


docker build -t dakobed/dakobed-vuejs-app .

docker run -it -p 8080:8080 --rm --name dockerize-vuejs-app-1 dakobed/dakobed-vuejs-app