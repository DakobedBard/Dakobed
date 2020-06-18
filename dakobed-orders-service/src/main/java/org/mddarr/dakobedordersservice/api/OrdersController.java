package org.mddarr.dakobedordersservice.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.mddarr.dakobedordersservice.models.OrderDocument;
import org.mddarr.dakobedordersservice.models.OrderEntity;
import org.mddarr.dakobedordersservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
public class OrdersController {

    private DynamoDBMapper dynamoDBMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;


    @RequestMapping(value="orders")
    public List<OrderEntity> getProduct(){
        return orderService.getOrders();
    }


//    @RequestMapping(value = "detail")
//    public OrderEntity orderDetail(@RequestParam("id") String id){
//        return orderService.getOrderDetail(id);
//    }
    @RequestMapping("detail")
    public List<OrderEntity> getOrderDetail(@RequestParam("id") String id, @RequestParam("date") String date){
//        orderService.getOrderByCustomer(id);
        return orderService.customerOrdersAfterDate(id, date);
    }

//    @RequestMapping("order")
//    public void getOrderByCustomer(@RequestParam("id") String id){
//        orderService.getOrderByID(id);
//    }
//
//
//    @RequestMapping("date-detail")
//    public long getDate(@RequestParam("date") long date){
//        return date;
//    }

//    @RequestMapping("date")
//    public String getDate(@RequestParam("date") long date){
//        Date d = new Date(date);
//        return d.toString();
//    }


}
