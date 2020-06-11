package org.mddarr.dakobedsnotel.writing.dao;


import org.mddarr.dakobedsnotel.writing.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);
}