package org.mddarr.dakobedproductservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
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

import java.util.Optional;

@SpringBootApplication
public class DakobedProductServiceApplication implements CommandLineRunner {

	public Boolean isEmpty(AmazonDynamoDB database, String tableName)  {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() ==0;
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
	@Override
	public void run(String... strings) throws Exception {

		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		CreateTableRequest ctr = dynamoDBMapper.generateCreateTableRequest(User.class)
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		tableWasCreatedForTest = TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);
		if (tableWasCreatedForTest) {
			logger.info("Created table {}", ctr.getTableName());
		}
		TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());
		logger.info("Table {} is active", ctr.getTableName());
		}


//
//

//
//
//
//
//		product = new ProductDocument();
//		product.setProductName("Rook 50");
//		product.setProductDescription("beginners trecking pack");
//		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/rook50.jpg");
//		product = productRepository.save(product);

//		logger.info("Saved product object: " + new Gson().toJson(product));
//
//
//		String awsServiceId = product.getId();




//		logger.info("AWS Service ID: " + awsServiceId);
//		Optional<ProductDocument> awsServiceQueried = productRepository.findById(awsServiceId);
//		if (awsServiceQueried.get() != null) {
//			logger.info("Queried object: " + new Gson().toJson(awsServiceQueried.get()));
//		}
//		Iterable<ProductDocument> awsServices = productRepository.findAll();
//		for (ProductDocument awsServiceObject : awsServices) {
//			logger.info("List object: " + new Gson().toJson(awsServiceObject));
//		}


}
