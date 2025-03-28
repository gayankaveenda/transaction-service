package com.tabcorp.transactionservice.dto;

import java.time.LocalDateTime;

public class TransactionDto {

    private LocalDateTime transactionTime;
    private Long customerId;
    private int quantity;
    private String productCode;

    // Constructors
    public TransactionDto() {
    }

    public TransactionDto(LocalDateTime transactionTime, Long customerId, int quantity, String productCode) {
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    // Getters and Setters
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
