package org.mddarr.dakobedordersservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;


@SpringBootApplication
public class DakobedOrdersServiceApplication implements CommandLineRunner {


	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedOrdersServiceApplication.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;



	public static void main(String[] args) {
		SpringApplication.run(DakobedOrdersServiceApplication.class, args);
	}

	public void createOrdersTable(DynamoDB dynamoDB){
		try {
			ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();

			attributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("id")
					.withAttributeType("S"));
			attributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("Date")
					.withAttributeType("S"));
			attributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("Price")
					.withAttributeType("N"));

// Table key schema
			ArrayList<KeySchemaElement> tableKeySchema = new ArrayList<KeySchemaElement>();
			tableKeySchema.add(new KeySchemaElement()
					.withAttributeName("id")
					.withKeyType(KeyType.HASH));  //Partition key
			tableKeySchema.add(new KeySchemaElement()
					.withAttributeName("Date")
					.withKeyType(KeyType.RANGE));  //Sort key

// PrecipIndex
			GlobalSecondaryIndex precipIndex = new GlobalSecondaryIndex()
					.withIndexName("Price")
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits((long) 10)
							.withWriteCapacityUnits((long) 1))
					.withProjection(new Projection().withProjectionType(ProjectionType.ALL));

			ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<KeySchemaElement>();

			indexKeySchema.add(new KeySchemaElement()
					.withAttributeName("Date")
					.withKeyType(KeyType.HASH));  //Partition key
			indexKeySchema.add(new KeySchemaElement()
					.withAttributeName("Price")
					.withKeyType(KeyType.RANGE));  //Sort key

			precipIndex.setKeySchema(indexKeySchema);

			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName("Dakobed-Orders")
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits((long) 5)
							.withWriteCapacityUnits((long) 1))
					.withAttributeDefinitions(attributeDefinitions)
					.withKeySchema(tableKeySchema)
					.withGlobalSecondaryIndexes(precipIndex);
			Table table = dynamoDB.createTable(createTableRequest);
			System.out.println(table.getDescription());

		}catch (Exception e){
			e.printStackTrace();
		}
	}


	public void LoadOrdersData(DynamoDB dynamoDB) throws IOException {

		Table table = dynamoDB.getTable("Dakobed-Orders");
		JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-orders-service/src/main/resources/orders.json"));
		JsonNode rootNode = new ObjectMapper().readTree(parser);
		Iterator<JsonNode> iter = rootNode.iterator();
		ObjectNode currentNode;

		while (iter.hasNext()) {

			currentNode = (ObjectNode) iter.next();
			double price = currentNode.path("price").asDouble();
			long order_time = currentNode.path("order_time").asLong();
			String productName = currentNode.path("productName").asText();
			String imageURL = currentNode.path("image_url").asText();
			String customerId = currentNode.path("customerId").asText();
			long quantity = currentNode.path("quantity").asLong();
			String state = currentNode.path("state").asText();

			DateTime date = new DateTime(Long.valueOf(order_time * 1000L), DateTimeZone.UTC);
			System.out.println("The date at which the oder occurs is " + date.toString());
			try {
				table.putItem(new Item().withPrimaryKey("id", UUID.randomUUID().toString(), "Date", date.toString())
						.withDouble("productName",price));
				System.out.println("PutItem succeeded: " + price + " " + productName);
			}
			catch (Exception e) {
				System.err.println("Unable to add product: " + price + " " + productName + " " + imageURL);
				System.err.println(e.getMessage());
				break;
			}
		}
		parser.close();
	}

	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() == 0;
	}

	@Override
	public void run(String... strings) throws Exception {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		createOrdersTable(dynamoDB);
		LoadOrdersData(dynamoDB);
//		if(isEmpty(amazonDynamoDB,"Dakobed-Products")){
//			LoadProductsTableData(dynamo
//	}
	}
}
