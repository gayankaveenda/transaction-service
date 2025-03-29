package com.tabcorp.transactionservice.dto;

public class TotalCostPerProductDto {
    private String productCode;
    private Long totalCost;

    public TotalCostPerProductDto(String productCode, Long totalCost) {
        this.productCode = productCode;
        this.totalCost = totalCost;
    }

    public TotalCostPerProductDto() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
}

