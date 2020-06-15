package org.mddarr.dakobedproductservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mddarr.dakobedproductservice.models.ProductDocument;
import org.mddarr.dakobedproductservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DakobedProductServiceApplication implements CommandLineRunner {


	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedProductServiceApplication.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(DakobedProductServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductDocument.class);

		tableRequest.setProvisionedThroughput(
				new ProvisionedThroughput(1L, 1L));

		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

		ProductDocument product = new ProductDocument();
		product.setProductName("Aether Pro 70");
		product.setProductDescription("Medium size trecking pack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/aetherpro70.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));


		product = new ProductDocument();
		product.setProductName("Archeon 45");
		product.setProductDescription("Small trecking pack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/archeon45.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));



		product = new ProductDocument();
		product.setProductName("Archeon 70");
		product.setProductDescription("Medium size trecking pack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/archeon70.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));


		product = new ProductDocument();
		product.setProductName("Atmos 50");
		product.setProductDescription("Small backpack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/atmos50.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));



		product = new ProductDocument();
		product.setProductName("Atmost 65");
		product.setProductDescription("Medium size trecking pack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/atmost65.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));


		product = new ProductDocument();
		product.setProductName("Aura 50");
		product.setProductDescription("Small backpack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/aura50.jpg");
		product = productRepository.save(product);
		logger.info("Saved product object: " + new Gson().toJson(product));




		product = new ProductDocument();
		product.setProductName("Rook 50");
		product.setProductDescription("beginners trecking pack");
		product.setImageURL("https://dakobed-osprety.s3-us-west-2.amazonaws.com/rook50.jpg");
		product = productRepository.save(product);

		logger.info("Saved product object: " + new Gson().toJson(product));


		String awsServiceId = product.getId();




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

}
