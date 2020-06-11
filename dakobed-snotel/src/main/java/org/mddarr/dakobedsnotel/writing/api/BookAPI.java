package org.mddarr.dakobedsnotel.writing.api;




import org.mddarr.dakobedsnotel.writing.dao.BookRepository;
import org.mddarr.dakobedsnotel.writing.dao.PageRepository;
import org.mddarr.dakobedsnotel.writing.entity.Book;
import org.mddarr.dakobedsnotel.writing.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookAPI {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PageRepository pageRepository;

    @GetMapping(value="getpages/{id}")
    public List<Page> getPageByBook(@PathVariable("isbn") String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        return pageRepository.findByBook(book);
    }

    @GetMapping(value="getbooks/{id}")
    public Book getBook(@PathVariable("isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @GetMapping(value="book/")
    public List<Page> getBooks(@RequestParam String isbn){
        Book book = bookRepository.findByIsbn(isbn);
        return pageRepository.findByBook(book);
    }


    @GetMapping(value="books")
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        System.out.println("Why " + books.size());
        return books;
    }
}
