package org.mddarr.dakobedservices;

import org.mddarr.dakobedservices.dao.DocumentRepository;
import org.mddarr.dakobedservices.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DakobedServicesApplication {

	@Autowired
	DocumentRepository documentRepository;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String[] arg0) throws Exception {
				Document document = new Document("7b562a76-669d-4189-b943-461e312ef2a1", "dakobed", "haroldDB","path");
				// save the book

				documentRepository.save(document);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DakobedServicesApplication.class, args);
	}

}
