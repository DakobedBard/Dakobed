package org.mddarr.dakobedproducts.repositories;


import org.mddarr.dakobedproducts.models.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, String> {
}