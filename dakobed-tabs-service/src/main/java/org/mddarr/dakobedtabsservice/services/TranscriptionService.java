package org.mddarr.dakobedtabsservice.services;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TranscriptionService {

    public static void createTabsTable(DynamoDB dynamoDB){
        String tableName = "Dakobed-Tabs";
        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withProvisionedThroughput(
                        new ProvisionedThroughput().withReadCapacityUnits((long) 1).withWriteCapacityUnits((long) 1));

        // Attribute definitions for table partition and sort keys
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("tabID").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("noteTime").withAttributeType("S"));


        createTableRequest.setAttributeDefinitions(attributeDefinitions);

        // Key schema for table
        ArrayList<KeySchemaElement> tableKeySchema = new ArrayList<KeySchemaElement>();
        tableKeySchema.add(new KeySchemaElement().withAttributeName("tabID").withKeyType(KeyType.HASH)); // Partition
        // key
        tableKeySchema.add(new KeySchemaElement().withAttributeName("noteTime").withKeyType(KeyType.RANGE)); // Sort
        // key

        createTableRequest.setKeySchema(tableKeySchema);

        System.out.println("Creating table " + tableName + "...");
        System.out.println(dynamoDB.createTable(createTableRequest));

        // Wait for table to become active
        System.out.println("Waiting for " + tableName + " to become ACTIVE...");
        try {
            Table table = dynamoDB.getTable(tableName);
            table.waitForActive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void loadOrdersData(DynamoDB dynamoDB) throws IOException {

        Table table = dynamoDB.getTable("Dakobed-Orders");
        JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-mir/data/orders.json"));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;
        int count = 0;
        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();
            double price = currentNode.path("price").asDouble();
            long order_time = currentNode.path("order_time").asLong();
            String customerID = currentNode.path("customerId").asText();
            String orderId = currentNode.path("orderID").asText();
            String order_status = currentNode.path("order_status").asText();
            String productsListString = currentNode.path("products").asText();

            ArrayList<String> productStringsList = new ArrayList<>(Arrays.asList(productsListString.split(",")));

            List<String> productsList = Arrays.asList(productsListString.split(","));
            Set<String> products = new HashSet<>(productsList);
            Item item;


            item = new Item().withPrimaryKey("CustomerId",customerID)
                    .withString("OrderId", orderId )
                    .withNumber("OrderCreationDate", order_time)
                    .withList("productIDs",productStringsList)
                    .withString("OrderStatus", order_status);

            DateTime date = new DateTime(Long.valueOf(order_time * 1000L), DateTimeZone.UTC);
            System.out.println("The date at which the oder occurs is " + date.toString());
            try {
                table.putItem(item);
                System.err.println("added product: " +  " " + products);
            }
            catch (Exception e) {
                System.err.println("Unable to add product: " + " " + products);
                System.err.println(e.getMessage());
                break;
            }
            count +=1;
        }
        parser.close();
    }

}
