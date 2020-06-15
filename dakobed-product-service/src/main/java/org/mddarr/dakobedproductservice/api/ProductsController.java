package org.mddarr.dakobedproductservice.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.mddarr.dakobedproductservice.models.ProductDocument;
import org.mddarr.dakobedproductservice.repositories.ProductRepository;
import org.mddarr.dakobedproductservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductsController {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "products")
    public List<ProductDocument> addProduct(){
        return productService.getAllProducts();
    }

}
