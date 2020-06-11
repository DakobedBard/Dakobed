package org.mddarr.dakobedsnotel.dao;


import org.mddarr.dakobedsnotel.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {


}