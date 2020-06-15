package org.mddarr.dakobedproducts;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.mddarr.dakobedproducts.models.Product;
import org.mddarr.dakobedproducts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DakobedProductsApplication implements CommandLineRunner {

	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedProductsApplication.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DakobedProductsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);

		tableRequest.setProvisionedThroughput(
				new ProvisionedThroughput(1L, 1L));

		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

		Product product = new Product();

		product.setProductName("Dakobed Product");
		product.setProductDescription("Stringy piece of shit");

		product = productRepository.save(product);

		logger.info("Saved AwsService object: " + new Gson().toJson(product));

		String awsServiceId = product.getId();

		logger.info("AWS Service ID: " + awsServiceId);

		Optional<Product> awsServiceQueried = productRepository.findById(awsServiceId);

		if (awsServiceQueried.get() != null) {
			logger.info("Queried object: " + new Gson().toJson(awsServiceQueried.get()));
		}

		Iterable<Product> awsServices = productRepository.findAll();

		for (Product awsServiceObject : awsServices) {
			logger.info("List object: " + new Gson().toJson(awsServiceObject));
		}
	}

}
