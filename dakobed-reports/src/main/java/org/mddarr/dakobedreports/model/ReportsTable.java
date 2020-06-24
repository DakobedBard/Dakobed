package org.mddarr.dakobedreports.model;

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

public class ReportsTable {

    public static String tableName = "Dakobed-Reports";

    public static void createTable(DynamoDB dynamoDB) {

        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withProvisionedThroughput(
                        new ProvisionedThroughput().withReadCapacityUnits((long) 1).withWriteCapacityUnits((long) 1));

        // Attribute definitions for table partition and sort keys
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("userID").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("ReportDate").withAttributeType("S"));


        createTableRequest.setAttributeDefinitions(attributeDefinitions);

        // Key schema for table
        ArrayList<KeySchemaElement> tableKeySchema = new ArrayList<KeySchemaElement>();
        tableKeySchema.add(new KeySchemaElement().withAttributeName("userID").withKeyType(KeyType.HASH)); // Partition
        // key
        tableKeySchema.add(new KeySchemaElement().withAttributeName("ReportDate").withKeyType(KeyType.RANGE)); // Sort
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

    public static void loadTripReports(DynamoDB dynamoDB) throws IOException {

        Table table = dynamoDB.getTable("Dakobed-Reports");
        JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-reports/src/main/resources/reports.json"));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();
            String reportDate = currentNode.path("ReportDate").asText();
            String reportName = currentNode.path("reportName").asText();
            String imageURL = currentNode.path("orderID").asText();
            String userID = currentNode.path("userID").asText();
            Item item;

            item = new Item().withPrimaryKey("userID",reportName)
                    .withString("ReportDate", reportDate)
                    .withString("imageURL",imageURL)
                    .withString("userID", userID);
            try {
                table.putItem(item);
                System.err.println("added product: " +  " " + reportDate);
            }
            catch (Exception e) {
                System.err.println("Unable to add product: " + " " + reportDate);
                System.err.println(e.getMessage());
                break;
            }

        }
        parser.close();
    }



//    public static void LoadData(){
//        Item().withPrimaryKey("CustomerId", "alice@example.com").withNumber("OrderId", 1)
//                .withNumber("IsOpen", 1).withNumber("OrderCreationDate", 20150101).withString("ProductCategory", "Book")
//                .withString("ProductName", "The Great Outdoors").withString("OrderStatus", "PACKING ITEMS");
//    }


}
