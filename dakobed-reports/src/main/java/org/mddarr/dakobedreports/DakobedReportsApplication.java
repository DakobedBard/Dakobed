package org.mddarr.dakobedreports;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.opsworks.model.Command;
import org.mddarr.dakobedreports.dynamo.ReportsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DakobedReportsApplication implements CommandLineRunner {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	public static void main(String[] args) {
		SpringApplication.run(DakobedReportsApplication.class, args);
	}

	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() == 0;
	}

	@Override
	public void run(String... strings) throws Exception {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		ReportsTable.loadTripReports(dynamoDB);

//
//		if(isEmpty(amazonDynamoDB,"Dakobed-Reports")){
//			ReportsTable.l(dynamoDB);
//		}


	}

}
