# Dakobed Orders Microservice

This spring boot microservice has endpoints for managing orders to an ECommerce event driven system


#### Query all orders from  customer w/ ID
curl localhost:8080/customer-orders?id=jerry@gmail.com | jq

#### Query all orders from customer w/ ID having been placed after date

localhost:8080/detail?id=jerry@gmail.com&date=20170603-25875 | jq


