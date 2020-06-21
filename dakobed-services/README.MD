#Dakobed Services


docker build . -t dakobed:dakobed-services
docker run --publish=8080:8080 dakobed:dakobed-services

docker push jasonheyward/dakobed:dakobed:dakobed-services
dakobeddb.cju35raiyeyw.us-west-2.rds.amazonaws.com
psql --host=dakobeddb.cju35raiyeyw.us-west-2.rds.amazonaws.com --port=5432 --username=postgres --password --dbname=dakobeddb 
psql --host=dakobed.cju35raiyeyw.us-west-2.rds.amazonaws.com --port=5432 --username=postgres --password --dbname=dakobed

nc dakobeddb.cju35raiyeyw.us-west-2.rds.amazonaws.com 5432