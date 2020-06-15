aws dynamodb query \
    --table-name dakobed-products \
    --endpoint-url http://localhost:8000 \
    --key-condition-expression "productName = 'Aether Pro 70'"

aws dynamodb scan --table-name dakobed-products
