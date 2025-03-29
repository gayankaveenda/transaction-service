package com.tabcorp.transactionservice.dto;

public class TotalCostPerCustomerDto {

    private Long customerId;
    private Long totalCost;

    public TotalCostPerCustomerDto() {
    }

    public TotalCostPerCustomerDto(Long customerId, Long totalCost) {
        this.customerId = customerId;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
}
