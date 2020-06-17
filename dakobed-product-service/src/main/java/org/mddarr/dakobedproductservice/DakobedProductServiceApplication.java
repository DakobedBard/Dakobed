package org.mddarr.dakobedproductservice;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mddarr.dakobedproductservice.models.ProductDocument;
import org.mddarr.dakobedproductservice.models.User;
import org.mddarr.dakobedproductservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class DakobedProductServiceApplication implements CommandLineRunner {

//	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
//		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
//		return database.scan(scanRequest).getScannedCount() == 0;
//	}
	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedProductServiceApplication.class);


	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DakobedProductServiceApplication.class, args);
	}

	private boolean tableWasCreatedForTest;

	public void createProductsTable(DynamoDB dynamoDB){
		String tableName = "Dakobed-Products";

		try {
			List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("price").withAttributeType("N"));
			List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));
			keySchema.add(new KeySchemaElement().withAttributeName("price").withKeyType(KeyType.RANGE));

			CreateTableRequest request = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(keySchema)
					.withAttributeDefinitions(attributeDefinitions)
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits(5L)
							.withWriteCapacityUnits(6L));

			Table table = dynamoDB.createTable(request);
			table.waitForActive();

		} catch (Exception e) {
			{
				System.err.println("Unable to create table: ");
				System.err.println(e.getMessage());
			}
		}
	}

	public void LoadProductsTableData(DynamoDB dynamoDB) throws IOException {

		Table table = dynamoDB.getTable("Dakobed-Products");
		JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-product-service/src/main/resources/products.json"));

		JsonNode rootNode = new ObjectMapper().readTree(parser);
		Iterator<JsonNode> iter = rootNode.iterator();
		ObjectNode currentNode;

		while (iter.hasNext()) {
			currentNode = (ObjectNode) iter.next();

			double price = currentNode.path("price").asDouble();
			String productName = currentNode.path("productName").asText();
			String imageURL = currentNode.path("image_url").asText();
			try {

				table.putItem(new Item().withPrimaryKey("Id", UUID.randomUUID().toString(), "price", price)
				.withString("productName",productName).withString("imageURL",imageURL));

//				table.putItem(new Item().withPrimaryKey("Id", UUID.randomUUID().toString(), "price", price).withJSON("productName",
//						productName).withJSON("imageURL", imageURL));
				System.out.println("PutItem succeeded: " + price + " " + productName);

			}
			catch (Exception e) {
				System.err.println("Unable to add movie: " + price + " " + productName + " " + imageURL);
				System.err.println(e.getMessage());
				break;
			}
		}
		parser.close();
	}



	@Override
	public void run(String... strings) throws Exception {
//		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//				.build();

		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		createProductsTable(dynamoDB);
		LoadProductsTableData(dynamoDB);


	}
}
//		try {
//			System.out.println("Attempting to create table; please wait...");
//			Table table = dynamoDB.createTable(tableName,
//					Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
//							// key
//							new KeySchemaElement("title", KeyType.RANGE)), // Sort key
//					Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
//							new AttributeDefinition("title", ScalarAttributeType.S)),
//					new ProvisionedThroughput(10L, 10L));
//			table.waitForActive();
//			System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
//
//		}
//		catch (Exception e) {
//			System.err.println("Unable to create table: ");
//			System.err.println(e.getMessage());
//		}

