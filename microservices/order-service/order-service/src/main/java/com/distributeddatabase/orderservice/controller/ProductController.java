package com.distributeddatabase.orderservice.controller;

import com.distributeddatabase.orderservice.dto.ProductRequest;
import com.distributeddatabase.orderservice.dto.ProductResponse;
import com.distributeddatabase.orderservice.model.Inventory;
import com.distributeddatabase.orderservice.model.Product;
import com.distributeddatabase.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private final ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        if(inventoryCheck(productRequest))
        {
            productService.createProduct(productRequest);
            updateInventory(productRequest);
        }

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }

    private boolean inventoryCheck(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .productId(productRequest.getProductId())
                .price(productRequest.getPrice())
                .build();

        //check if this product is in inventory by productId
        String url = "http://localhost:8081/api/inventory/" + product.getProductId() + "/check?requiredQuantity=" + product.getQuantity();
        Boolean available = restTemplate.getForObject(url, Boolean.class);
        if (available == null || !available)
        {
            return false;
        }
        return true;
    }

    private void updateInventory(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .productId(productRequest.getProductId())
                .price(productRequest.getPrice())
                .build();

        String getUrl = "http://localhost:8081/api/inventory/" + product.getProductId();
        Inventory response = restTemplate.getForObject(getUrl, Inventory.class);



        int totalQuantity =  response.getQuantity();
        int requiredQuantity = product.getQuantity();

        response.setQuantity(totalQuantity-requiredQuantity);

        String putUrl = "http://localhost:8081/api/inventory/put";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(response, headers);
        ResponseEntity<Void> resp1 = restTemplate.exchange(
                putUrl,
                HttpMethod.PUT,
                requestEntity,
                Void.class);
        if (resp1.getStatusCode() == HttpStatus.OK) {
            System.out.println("Hurray Success");
        } else {
            System.out.println("Shit Error");
        }
    }

}
