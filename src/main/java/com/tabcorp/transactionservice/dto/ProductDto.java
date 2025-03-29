package com.tabcorp.transactionservice.dto;

import com.tabcorp.transactionservice.util.ProductActive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDto {

    @NotBlank(message = "Product Code is required")
    private String productCode;

    @PositiveOrZero(message = "Product Cost cannot be negative")
    private int cost;

    @ProductActive
    private String status;

    // Constructors
    public ProductDto() {
    }

    public ProductDto(String productCode, int cost, String status) {
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
