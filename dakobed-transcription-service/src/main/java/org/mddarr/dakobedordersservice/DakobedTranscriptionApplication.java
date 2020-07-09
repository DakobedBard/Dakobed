package org.mddarr.dakobedordersservice;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.opsworks.model.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.mddarr.dakobedordersservice.services.S3Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;

@SpringBootApplication
public class DakobedTranscriptionApplication implements CommandLineRunner {

	@Autowired
	AmazonDynamoDB amazonDynamoDB;

	@Autowired
	S3Factory s3Factory;

	private DynamoDBMapper dynamoDBMapper;

	private static final Logger logger = LogManager.getLogger(DakobedTranscriptionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DakobedTranscriptionApplication.class, args);
	}

	public Boolean isEmpty(AmazonDynamoDB database, String tableName) {
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
		return database.scan(scanRequest).getScannedCount() == 0;
	}

	@Override
	public void run(String... args) throws Exception {
//		byte[] a = s3Factory.getFile();
//		try (FileOutputStream fos = new FileOutputStream("src/main/resources/trans1.json")) {
//			fos.write(a);
//			//fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		System.out.println("The byte array is " + a.length);
	}
}


