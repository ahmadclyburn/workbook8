package com.pluralsight.model;

public class Product {
    private String productId;
    private String productName;
    private double unitPrice;
    private int unitsInStock;

    public Product(String productId, String productName, double unitPrice, int unitsInStock) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }
}
