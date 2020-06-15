package org.mddarr.dakobedproductservice.repositories;


import org.mddarr.dakobedproductservice.models.ProductDocument;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<ProductDocument, String> {
}