package org.mddarr.dakobedreportservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import org.mddarr.dakobedreportservice.model.ReportsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DakobedReportServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DakobedReportServiceApplication.class, args);
	}

	@Autowired
	AmazonDynamoDB amazonDynamoDB;


	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() == 0;
	}

	@Override
	public void run(String... strings) throws Exception {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
//		ReportsTable.createTable(dynamoDB);
		ReportsTable.loadTripReports(dynamoDB);
	}

}
