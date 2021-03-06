# Dakobed portfolio
My portfolio named after my favorite mountain Glacier Peak also known as Dakobed  

This repository contains all of my projects integrated into one.  The projects include various data pipelines & Spring Boot APIs as well as a Vue front end to interact with the various projects.  Some of the individual projects are incomplete but as I am actively working on this, I hope to have these fleshed out a bit better soon.  Many of the applications have been containerized and made available to run using AWS Elastic Container Service Fargate instances.  I am working to route traffic to a domain-name I have purchased but am still working on this!.     



[Dakobed Music Information Retrieval](dakobed-mir/README.MD)
- Train keras neural networks for making predictions on piano using Maestro dataset & the GuitarSet data audio files
- Train model on EC2 GPU instance
 
[Dakobed Snotel Pipeline](dakobed-snotel-service/README.MD)
- Scrape USDA snowpack & streamflow data and insert into dynamoDB database.
- Spring boot REST API for querying the data


[Dakobed Twitter Pipeline](dakobed-twitter-producer/README.MD)
- Push tweets into Kafka topic using Java & twitter4j library
- Use kafka connect to push data into elastic search
- Spring API routes for querying tweets in ElasticSearch by location & keywords


[Dakobed Neural Style Transfer](dakobed-style/README.MD)
- Perform neural style tranfer using Keras
- Train network on EC2 GPU isntances


[Dakobed Trip Reports](dakobed-report-service/README.MD)
- Scrape Washington Trails Association trip reports into postgreSQL using psycopg2
- Use Debezium CDC source connector to move data into Kafka then sink connector to ElasticSearch
- Spring API for querying the trip reports
- API for posting & serving my personal trip reports 


[Dakobed ECommerce Microservices](dakobed-orders-service/README.MD)
- Event driven microservices architecture using Spring Kafka Streams 
- Use AVRO serialization 


[Dakobed Vue Frontend](dakobed-vue/README.MD)
- Frontend website for interacting with the API's provided in other projects
- technologies used: vue, vuetify, vuex, google maps javascript API, D3
- Containerized & run on AWS ECS Fargate instances
