package com.distritubuteddatabase.shoppingservice.repository;

import com.distritubuteddatabase.shoppingservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}