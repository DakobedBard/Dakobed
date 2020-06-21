# Dakobed portfolio


[Dakobed Music Information Retrieval](dakobed-mir/README.MD)
- Train keras neural networks for making predictions on piano using Maestro dataset & the GuitarSet data audio files
- Train model on EC2 GPU instance
 
[Dakobed Snotel Pipeline](dakobed-snotel-service/README.MD)
- Scrape USDA snowpack & streamflow data and insert into dynamoDB database.
- Spring boot REST API for querying the data


[Dakobed Twitter Pipeline](dakobed-twitter-service/README.MD)
- Push tweets into Kafka topic using Java & twitter4j library
- Use kafka connect to push data into elastic search
- Spring API routes for querying tweets in ElasticSearch by location & keywords


[Dakobed Neural Style Transfer](dakobed-style/README.MD)
- Perform neural style tranfer using Keras
- Train network on EC2 GPU isntances


[Dakobed ECommerce Microservices](dakobed-orders-service/README.MD)
- Event driven microservices architecture using Spring Kafka Streams 
- Use AVRO serialization 

[Dakobed Trip Reports](dakobed-reports/README.MD)
- Scrape Washington Trails Association trip reports into postgreSQL using psycopg2
- Use Debezium CDC source connector to move data into Kafka then sink connector to ElasticSearch
- Spring API for querying the trip reports
- API for posting & serving my personal trip reports 


[Dakobed Vue Frontend](dakobed-vue/README.MD)
- Frontend website for interacting with the API's provided in other projects
- technologies used: vue, vuetify, vuex, google maps javascript API, D3
- Containerized & run on AWS ECS Fargate instances
