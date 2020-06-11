package org.mddarr.dakobedsnotel.writing.dao;


import org.mddarr.dakobedsnotel.writing.entity.Book;

import org.mddarr.dakobedsnotel.writing.entity.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {

    List<Page> findByBook(Book book); //, Sort sort);
}
