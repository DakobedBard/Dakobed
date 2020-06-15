package org.mddarr.dakobedproductservice.repositories;


import org.mddarr.dakobedproductservice.models.OrderDocument;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface OrderRepository extends CrudRepository<OrderDocument, String> {
}