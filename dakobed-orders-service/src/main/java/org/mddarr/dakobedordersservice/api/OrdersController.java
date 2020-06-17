package org.mddarr.dakobedordersservice.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.mddarr.dakobedordersservice.models.OrderDocument;
import org.mddarr.dakobedordersservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrdersController {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @RequestMapping(value = "products")
    public String addProduct(){
        return "dingus";
    }

    @RequestMapping(value="orders")
    public List<OrderDocument> getProduct(){
        return orderService.getOrders();
    }
}
