package org.mddarr.dakobedtabsservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.mddarr.dakobedtabsservice.dynamo.TabsTable;
import org.mddarr.dakobedtabsservice.models.Transcription;
import org.mddarr.dakobedtabsservice.services.TabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DakobedTranscriptionServiceApplication implements CommandLineRunner {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	@Autowired
	TabsService tabsService;

	public static void main(String[] args) {
		SpringApplication.run(DakobedTranscriptionServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

//		Transcription transcription =  tabsService.getTab();

		//TabsTable.createTable(dynamoDB);
//		TabsTable.loadOrdersData(dynamoDB);
//		ReportsTable.createTable(dynamoDB);
//		ReportsTable.loadTripReports(dynamoDB);
	}

}
