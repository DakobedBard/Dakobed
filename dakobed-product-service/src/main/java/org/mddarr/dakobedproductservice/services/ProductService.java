package org.mddarr.dakobedproductservice.services;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.mddarr.dakobedproductservice.models.ProductDocument;

import org.mddarr.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    AmazonDynamoDB amazonDynamoDB;


    @Autowired
    KafkaTemplate<String, Product> kafkaTemplateProduct;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

//    public List<ProductDocument> getAllProducts(){
//        List<ProductDocument> products = new ArrayList<>();
//        productRepository.findAll().forEach(products::add);
//        return products;
//    }


    public List<ProductDocument> getProducts(){

        // Change to your Table_Name (you can load dynamically from lambda env as well)
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder().withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("Dakobed-Products")).build();

        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
// Change to your model class
        List <ProductDocument> products = mapper.scan(ProductDocument.class, scanExpression);

// Check the count and iterate the list and perform as desired.
        return products;

    }




}
