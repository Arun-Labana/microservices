package com.distributeddatabase.orderservice.model;

public class Inventory {
    private String id;
    private String name;
    private String productId;
    private int quantity;

    // Constructor
    public Inventory(String id, String name, String productId, int quantity) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
