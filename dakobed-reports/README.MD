#Dakobed Trip Reports

## WTA Trip Reports Pipeline
## Run the scraper and push to postgres 
docker-compose -f wta-scrape-compose.yaml



#### Add the ElasticSearch sink connector
bash scripts/post.sh es.json
#### Verify that ES is getting populated
bash scripts/queryES.sh db.snowpack.trip_reports








## WTA Trip Reports Spring Service

Trip Reports Spring Service Elastic Container Registry

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 710339184759.dkr.ecr.us-west-2.amazonaws.com

docker build -t dakobed-report-service .
docker tag dakobed-report-service:latest 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-report-service:latest
docker push 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-report-service:latest

psql --host=dakobed.cju35raiyeyw.us-west-2.rds.amazonaws.com --port=5432 --username=postgres --password --dbname=dakobed



