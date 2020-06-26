package org.mddarr.dakobedtabsservice.dynamo;

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


public class TabsTable {
    public static String tableName = "Dakobed-Tabs";

    public static void createTable(DynamoDB dynamoDB) {

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

        Table table = dynamoDB.getTable("Dakobed-Tabs");
        JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-mir/data/dakobed-tabs/fileID0.json"));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;
        int count = 0;
        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();
            String time = currentNode.path("time").asText();
            String duration = currentNode.path("duration").asText();
            String midi = currentNode.path("midi").asText();
            String string = currentNode.path("string").asText();

            Item item;


            item = new Item().withPrimaryKey("tabID","0")
                    .withString("noteTime", time )
                    .withString("duration", duration)
                    .withList("midi",midi)
                    .withString("string", string);

            try {
                table.putItem(item);
                System.err.println("added product: " +  " " + midi);
            }
            catch (Exception e) {
                System.err.println("Unable to add product: " + " " + midi);
                System.err.println(e.getMessage());
                break;
            }
            count +=1;
        }
        System.out.println("The count is " + count);
        parser.close();
    }



//    public static void LoadData(){
//        Item().withPrimaryKey("CustomerId", "alice@example.com").withNumber("OrderId", 1)
//                .withNumber("IsOpen", 1).withNumber("OrderCreationDate", 20150101).withString("ProductCategory", "Book")
//                .withString("ProductName", "The Great Outdoors").withString("OrderStatus", "PACKING ITEMS");
//    }


}
