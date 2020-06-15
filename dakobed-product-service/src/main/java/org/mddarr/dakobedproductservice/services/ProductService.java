package org.mddarr.dakobedproductservice.services;


import org.mddarr.dakobedproductservice.models.ProductDocument;
import org.mddarr.dakobedproductservice.repositories.ProductRepository;
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
    ProductRepository productRepository;

    @Autowired
    KafkaTemplate<String, Product> kafkaTemplateProduct;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDocument> getAllProducts(){
        List<ProductDocument> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;

    }

}
