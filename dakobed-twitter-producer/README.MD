#Dakobed Twitter Producer

Multithreaded Java application that streams tweets using twitter4j library in one thread, and produces to a Kafka topic in the other thread.  

docker build . -t dakobed:dakobed-twitter-producer
docker run  dakobed:dakobed-twitter-producer