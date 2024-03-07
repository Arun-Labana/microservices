package com.distributeddatabase.orderservice.repository;

import com.distributeddatabase.orderservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}