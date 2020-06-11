package org.mddarr.dakobedservices.dao;


import org.mddarr.dakobedservices.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends CrudRepository<Document, String> {


}