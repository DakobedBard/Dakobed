# Dakobed Trip Reports


This project contains a pipeline of WTA trip reports into ElasticSearch using Kafka connect.  Initially the trip reports are scraped 
using the python beautiful soup libray & insterted into a postgreSQL database using pyscopg2.  Once inserted to postgres, the Debezium
postgres Kafka connect connector will produce messages to a Kafka topic, & inserted into an ElasticSearch index using a sink connector.



#### Run WTA Trip Reports Pipeline

<img src="https://dakobed-style.s3-us-west-2.amazonaws.com/wta.png" width="220" height="200">

Run the containerized pipeline.  This compose fil

docker-compose -f wta-scrape-compose.yaml up --build 

python3 dakobed-reports/wta/scrapeWTA.py 0


##### Add the ElasticSearch sink connector & verify that ES is getting populated

curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-reports/wta/connectors/debezium_source_connector.json

bash scripts/queryES.sh db.snowpack.trip_reports



#### WTA Trip Reports Spring Service

Trip Reports Spring Service Elastic Container Registry

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 710339184759.dkr.ecr.us-west-2.amazonaws.com

docker build -t dakobed-report-service .
docker tag dakobed-report-service:latest 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-report-service:latest
docker push 710339184759.dkr.ecr.us-west-2.amazonaws.com/dakobed-report-service:latest

psql --host=dakobed.cju35raiyeyw.us-west-2.rds.amazonaws.com --port=5432 --username=postgres --password --dbname=dakobed





#### My Personal Trip Reports Service 

aws dynamodb list-tables --endpoint-url http://localhost:8000

aws dynamodb scan --table-name Dakobed-Reports --endpoint-url http://localhost:8000
