package com.distributeddatabase.orderservice.service;

import com.distributeddatabase.orderservice.dto.ProductRequest;
import com.distributeddatabase.orderservice.dto.ProductResponse;
import com.distributeddatabase.orderservice.model.Product;
import com.distributeddatabase.orderservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .productId(productRequest.getProductId())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productId(product.getProductId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
