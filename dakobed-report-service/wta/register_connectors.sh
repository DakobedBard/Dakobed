#!/bin/bash

docker exec connect confluent-hub install  debezium/debezium-connector-postgresql:0.9.4 --no-prompt
docker restart connect
sleep 20
curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-reports/wta/connectors/debezium_source_connector.json
#curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-reports/wta/connectors/elasticsearch_sink_connector.json

