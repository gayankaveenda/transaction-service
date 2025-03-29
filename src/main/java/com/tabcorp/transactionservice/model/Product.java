package com.tabcorp.transactionservice.model;

import com.tabcorp.transactionservice.util.ProductActive;
import com.tabcorp.transactionservice.util.TotalCost;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    private String productCode;

    @TotalCost
    private int cost;

    @ProductActive
    private String status;  // "Active" or "Inactive"

    public Product() {
    }

    public Product(String productCode, int cost, String status) {
        this.productCode = productCode;
        this.cost = cost;
        this.status = status;
    }

    // Getters and Setters
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
