package com.catalog_service.repository;

import com.catalog_service.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNomeContainingIgnoreCase(String nome);
}
