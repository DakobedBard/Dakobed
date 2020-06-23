#!/bin/bash
curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-reports/connectors/debezium_source_connector.json
curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-reports/connectors/elasticsearch_sink.connector.json