package org.mddarr.dakobedordersservice.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


import org.mddarr.dakobedordersservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
public class TranscriptionController {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello";
    }



    @RequestMapping(value = "uuid")
    public void genUUIDS(){
        for(int i =0; i<50; i ++){
            System.out.println(UUID.randomUUID().toString());
        }
    }

}
