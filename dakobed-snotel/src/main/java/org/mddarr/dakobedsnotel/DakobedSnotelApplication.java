package org.mddarr.dakobedsnotel;

import org.mddarr.dakobedsnotel.writing.dao.BookRepository;
import org.mddarr.dakobedsnotel.writing.dao.PageRepository;
import org.mddarr.dakobedsnotel.writing.entity.Book;
import org.mddarr.dakobedsnotel.writing.entity.Page;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DakobedSnotelApplication {

//	@Bean
//	public CommandLineRunner mappingDemo(BookRepository bookRepository,
//										 PageRepository pageRepository) {
//		return args -> {
//
//			// create a new book
//			Book book = new Book("Java 101", "John Doe", "1234561");
//
//			// save the book
//			bookRepository.save(book);
//
//			// create and save new pages
//			pageRepository.save(new Page(1, "Introduction contents", "Introduction", book));
//			pageRepository.save(new Page(65, "Java 8 contents", "Java 8", book));
//			pageRepository.save(new Page(95, "Concurrency contents", "Concurrency", book));
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(DakobedSnotelApplication.class, args);
	}

}
