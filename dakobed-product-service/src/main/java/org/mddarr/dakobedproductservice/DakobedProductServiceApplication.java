package org.mddarr.dakobedproductservice;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DakobedProductServiceApplication implements CommandLineRunner {

	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() == 0;
	}

	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedProductServiceApplication.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DakobedProductServiceApplication.class, args);
	}

	private boolean tableWasCreatedForTest;

	public void createProductsTable(DynamoDB dynamoDB){
		String tableName = "Products";

		try {
			List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("N"));

			List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));

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



	@Override
	public void run(String... strings) throws Exception {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
				.build();

		DynamoDB dynamoDB = new DynamoDB(client);
		if(isEmpty(client, "Products")){
			createProductsTable(dynamoDB);
		}


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

