package com.distributeddatabase.inventoryservice.controller;

import com.distributeddatabase.inventoryservice.model.Product;
import com.distributeddatabase.inventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;




@RestController
@RequestMapping("/api/inventory")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProductItem(@RequestBody Product product)
    {

        return productService.createProductItem(product);
    }

    @PutMapping("/put")
    public Product updateInventoryItem(@RequestBody Product product)
    {
        return productService.updateProductItem(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductItem(@PathVariable String id)
    {
        return productService.getProductItem(id);
    }

    @GetMapping("/{id}/check")
    public boolean checkStockAvailability(@PathVariable String id,@RequestParam int requiredQuantity)
    {
        return productService.checkStockAvailability(id,requiredQuantity);
    }
}
