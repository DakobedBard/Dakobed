package org.mddarr.dakobedordersservice.dynamo;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;

public class OrdersTable {
    public static String tableName = "Dakobed-Orders";
    public static void createTable(DynamoDB dynamoDB) {

        // Attribute definitions
        ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

        attributeDefinitions.add(new AttributeDefinition().withAttributeName("IssueId").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("Title").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("CreateDate").withAttributeType("S"));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("DueDate").withAttributeType("S"));

        // Key schema for table
        ArrayList<KeySchemaElement> tableKeySchema = new ArrayList<KeySchemaElement>();
        tableKeySchema.add(new KeySchemaElement().withAttributeName("IssueId").withKeyType(KeyType.HASH)); // Partition
        // key
        tableKeySchema.add(new KeySchemaElement().withAttributeName("Title").withKeyType(KeyType.RANGE)); // Sort
        // key

        // Initial provisioned throughput settings for the indexes
        ProvisionedThroughput ptIndex = new ProvisionedThroughput().withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L);

        // CreateDateIndex
        GlobalSecondaryIndex createDateIndex = new GlobalSecondaryIndex().withIndexName("CreateDateIndex")
                .withProvisionedThroughput(ptIndex)
                .withKeySchema(new KeySchemaElement().withAttributeName("CreateDate").withKeyType(KeyType.HASH), // Partition
                        // key
                        new KeySchemaElement().withAttributeName("IssueId").withKeyType(KeyType.RANGE)) // Sort
                // key
                .withProjection(
                        new Projection().withProjectionType("INCLUDE").withNonKeyAttributes("Description", "Status"));

        // TitleIndex
        GlobalSecondaryIndex titleIndex = new GlobalSecondaryIndex().withIndexName("TitleIndex")
                .withProvisionedThroughput(ptIndex)
                .withKeySchema(new KeySchemaElement().withAttributeName("Title").withKeyType(KeyType.HASH), // Partition
                        // key
                        new KeySchemaElement().withAttributeName("IssueId").withKeyType(KeyType.RANGE)) // Sort
                // key
                .withProjection(new Projection().withProjectionType("KEYS_ONLY"));

        // DueDateIndex
        GlobalSecondaryIndex dueDateIndex = new GlobalSecondaryIndex().withIndexName("DueDateIndex")
                .withProvisionedThroughput(ptIndex)
                .withKeySchema(new KeySchemaElement().withAttributeName("DueDate").withKeyType(KeyType.HASH)) // Partition
                // key
                .withProjection(new Projection().withProjectionType("ALL"));

        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withProvisionedThroughput(
                        new ProvisionedThroughput().withReadCapacityUnits((long) 1).withWriteCapacityUnits((long) 1))
                .withAttributeDefinitions(attributeDefinitions).withKeySchema(tableKeySchema)
                .withGlobalSecondaryIndexes(createDateIndex, titleIndex, dueDateIndex);

        System.out.println("Creating table " + tableName + "...");
        dynamoDB.createTable(createTableRequest);

        // Wait for table to become active
        System.out.println("Waiting for " + tableName + " to become ACTIVE...");
        try {
            Table table = dynamoDB.getTable(tableName);
            table.waitForActive();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void LoadData(){
//        Item().withPrimaryKey("CustomerId", "alice@example.com").withNumber("OrderId", 1)
//                .withNumber("IsOpen", 1).withNumber("OrderCreationDate", 20150101).withString("ProductCategory", "Book")
//                .withString("ProductName", "The Great Outdoors").withString("OrderStatus", "PACKING ITEMS");
//    }


}
