package com.distributeddatabase.inventoryservice.repository;

import com.distributeddatabase.inventoryservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductId(String productId);
}
