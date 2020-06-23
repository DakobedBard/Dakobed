#Dakobed Twitter Producer

Multithreaded Java application that streams tweets using twitter4j library in one thread, and produces to a Kafka topic in the other thread.  


docker-compose up -f kafka-es-stack-compose.yaml up --build
java -jar dakobed-twitter-producer/target/dakobed-twitter-producer-1.0-SNAPSHOT.jar virus

bash dakobed-twitter-producer/


#### Consume the tweets w/ the local consumer..
confluent local consume kafka-tweets



docker build . -t dakobed:dakobed-twitter-producer
docker run  dakobed:dakobed-twitter-producer

confluent kafka-avro-console-producer \
--broker-list localhost:9092 --topic test-elasticsearch-sink \
--property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"}]}'