package com.distritubuteddatabase.shoppingservice.controller;

import com.distritubuteddatabase.shoppingservice.dto.ProductRequest;
import com.distritubuteddatabase.shoppingservice.dto.ProductResponse;
import com.distritubuteddatabase.shoppingservice.model.Inventory;
import com.distritubuteddatabase.shoppingservice.model.Product;
import com.distritubuteddatabase.shoppingservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        Product p1 = productService.createProduct(productRequest);
        createInventory(p1);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }

    public void createInventory(Product product)
    {

        String url = "http://localhots:8081/api/inventory";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Inventory inventory = new Inventory();

        inventory.setProductId(product.getId());
        inventory.setName(product.getName());
        inventory.setQuantity(1);

        HttpEntity<Inventory> requestEntity = new HttpEntity<>(inventory, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, requestEntity, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Inventory created successfully.");
        } else {
            System.err.println("Failed to create inventory.");
        }
    }

}
