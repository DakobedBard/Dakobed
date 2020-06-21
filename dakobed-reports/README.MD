#Dakobed Trip Reports


docker build . -t dakobed:dakobed-reports
docker run --publish=8083:8083 dakobed:dakobed-reports

psql --host=dakobeddb.cju35raiyeyw.us-west-2.rds.amazonaws.com \
   --port=5432 \
   --username=postgres \
   --password=postgres \
   --dbname=dakobeddb 
   

psql --host=dakobed.cju35raiyeyw.us-west-2.rds.amazonaws.com --port=5432 --username=postgres --password --dbname=dakobed

jdbc:postgresql://@pellefant.db.elephantsql.com:5432/cwkqmdql?user=cwkqmdql&password=SsVqwdLxQObgaJAYu68O-8gTY1VmS9LX&sslmode=require