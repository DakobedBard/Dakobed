## Dakobed Twitter Producer

Multithreaded Java application that streams tweets using twitter4j library in one thread, and produces to a Kafka topic in the other thread.  

#### Run the twitter pipeline locally
mvn clean package 
docker-compose -f tweets-compose.yaml up --build

#### Consume the tweets with kafka console consumer
docker exec kafka bash bin/kafka-console-consumer.sh --topic kafka-tweets --from-beginning --property print.key=true --bootstrap-server kafka:9092

curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-twitter-producer/connectors/es_sink.json

bash scripts/elasticsearch/indices.sh kafka-tweets


docker build . -t dakobed:dakobed-twitter-producer

curl -i -X POST -H "Accept:application/json" -H  "Content-Type:application/json" http://localhost:8083/connectors/ -d @dakobed-twitter-producer/connectors/es_sink.json
