package com.distributeddatabase.inventoryservice.service;

import com.distributeddatabase.inventoryservice.model.Product;
import com.distributeddatabase.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product createProductItem(Product product)
    {
        return productRepository.save(product);
    }

    public Product updateProductItem(Product product)
    {
        Query query = new Query().addCriteria(Criteria.where("productId").is(product.getProductId()));
        Update update = new Update().set("quantity",product.getQuantity());
        mongoTemplate.updateFirst(query,update,Product.class);
        return product;
    }

    public Optional<Product> getProductItem(String id)
    {
        return productRepository.findByProductId(id);
    }

    public boolean checkStockAvailability(String id, int requiredQuantity)
    {
        Optional<Product> optionalProduct = productRepository.findByProductId(id);
        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            return product.getQuantity() >= requiredQuantity;
        }
        return false;
    }
}
