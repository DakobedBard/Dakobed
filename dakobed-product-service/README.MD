aws dynamodb query \
    --table-name dakobed-products \
    --endpoint-url http://localhost:8000 \
    --key-condition-expression "productName = 'Aether Pro 70'"

aws dynamodb scan --table-name Products --endpoint-url http://localhost:8000
aws dynamodb scan --table-name Movies --endpoint-url http://localhost:8000

 
aws dynamodb list-tables --endpoint-url http://localhost:8000

