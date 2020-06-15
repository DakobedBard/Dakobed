package org.mddarr.dakobedproductservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mddarr.dakobedproductservice.models.OrderDocument;
import org.mddarr.dakobedproductservice.repositories.OrderRepository;
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
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(DakobedProductServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(OrderDocument.class);

		tableRequest.setProvisionedThroughput(
				new ProvisionedThroughput(1L, 1L));

		TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

		OrderDocument order = new OrderDocument();

		order.setProductName("Dakobed Product");
		order.setProductDescription("Stringy piece of shit");

		order = orderRepository.save(order);

		logger.info("Saved AwsService object: " + new Gson().toJson(order));

		String awsServiceId = order.getId();

		logger.info("AWS Service ID: " + awsServiceId);

		Optional<OrderDocument> awsServiceQueried = orderRepository.findById(awsServiceId);

		if (awsServiceQueried.get() != null) {
			logger.info("Queried object: " + new Gson().toJson(awsServiceQueried.get()));
		}

		Iterable<OrderDocument> awsServices = orderRepository.findAll();

		for (OrderDocument awsServiceObject : awsServices) {
			logger.info("List object: " + new Gson().toJson(awsServiceObject));
		}
	}

}
