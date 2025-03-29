package com.tabcorp.transactionservice.dto;

import com.tabcorp.transactionservice.util.FutureDate;
import com.tabcorp.transactionservice.util.ValidTransactionCost;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@ValidTransactionCost
public class TransactionDto {

    @FutureDate
    private LocalDateTime transactionTime;

    @NotNull
    @Positive
    private Long customerId;

    @Positive
    @NotNull
    private int quantity;

    private String productCode;

    private Long transactionId;

    // Constructors
    public TransactionDto() {
    }

    public TransactionDto(LocalDateTime transactionTime, Long customerId, int quantity, String productCode) {
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    public TransactionDto(LocalDateTime transactionTime, Long customerId, int quantity, String productCode, Long transactionId) {
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
        this.transactionId = transactionId;
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

    public Long getTransactionId() {
        return transactionId;
    }

}
