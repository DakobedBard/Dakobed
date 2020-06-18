package org.mddarr.dakobedordersservice.services;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.amazonaws.services.dynamodbv2.model.Select;
import org.mddarr.dakobedordersservice.models.OrderDocument;
import org.mddarr.dakobedordersservice.models.OrderEntity;
import org.mddarr.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Autowired
    KafkaTemplate<String, Product> kafkaTemplateProduct;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
//
//    public List<OrderDocument> getOrdersBetweenDates(){
//        DynamoDBQueryExpression<OrderDocument> query = new DynamoDBQueryExpression<OrderDocument>()
//                .withHashKeyValues()
//    }



    public List<OrderEntity> getOrders(){

        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("Dakobed-Orders")).build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List <OrderEntity> orders = mapper.scan(OrderEntity.class, scanExpression);
        return orders;
    }

    public OrderEntity getOrderByID(String id){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        OrderEntity order = mapper.load(OrderEntity.class, id );
        return order;
    }


    public void getOrderByCustomer(String customerID){
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        Table table = dynamoDB.getTable("Dakobed-Orders");


        QuerySpec querySpec = new QuerySpec().withConsistentRead(true).withScanIndexForward(true)
                .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);

        querySpec.withKeyConditionExpression("CustomerId = :v_custid")
                .withValueMap(new ValueMap().withString(":v_custid", customerID));

        ItemCollection<QueryOutcome> items = table.query(querySpec);
        Iterator<Item> iterator = items.iterator();

        System.out.println("Query: printing results...");

        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    }


    public void getOrderByCustomerBetweenDates(String customerID){

        QuerySpec querySpec = new QuerySpec().withConsistentRead(true).withScanIndexForward(true)
                .withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable("Dakobed-Orders");
        Index index = table.getIndex("OrderCreationDateIndex");

        querySpec.withKeyConditionExpression("CustomerId = :v_custid and OrderCreationDate >= :v_orddate")
                .withValueMap(
                        new ValueMap().withString(":v_custid", customerID).withNumber(":v_orddate", 20150131));

        querySpec.withSelect(Select.ALL_PROJECTED_ATTRIBUTES);

        ItemCollection<QueryOutcome> items = index.query(querySpec);
        Iterator<Item> iterator = items.iterator();

        System.out.println("Query: printing results...");

        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    }


//    public void getOrdersByCustomer(String customerID){
//        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
//        Table table = dynamoDB.getTable('Dakobed-Orders');
//        Index index = table.getIndex(indexName);
//
//        querySpec.withKeyConditionExpression("CustomerId = :v_custid and OrderCreationDate >= :v_orddate")
//                .withValueMap(
//                        new ValueMap().withString(":v_custid", "bob@example.com").withNumber(":v_orddate", 20150131));
//
//        querySpec.withSelect(Select.ALL_PROJECTED_ATTRIBUTES);
//
//        ItemCollection<QueryOutcome> items = index.query(querySpec);
//        Iterator<Item> iterator = items.iterator();
//
//        System.out.println("Query: printing results...");
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().toJSONPretty());
//        }
//    }



    public OrderEntity getOrderDetail(String id){
        OrderEntity order = new OrderEntity();
        order.setOrderId(id);
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        DynamoDBQueryExpression<OrderEntity> queryExpression = new DynamoDBQueryExpression<OrderEntity>()
                .withHashKeyValues(order);
        List<OrderEntity> itemList = mapper.query(OrderEntity.class, queryExpression);

        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(itemList.get(i).getCustomerId());
            System.out.println(itemList.get(i).getOrderId());
        }
        return itemList.get(0);
    }

}
