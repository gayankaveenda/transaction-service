package com.tabcorp.transactionservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime transactionTime;

    @Column(name = "customer_id")
    private Long customerId;

    private int quantity;

    @Column(name = "product_code")
    private String productCode;

    public Transaction() {
    }

    public Transaction(Long id, LocalDateTime transactionTime, Long customerId, int quantity, String productCode) {
        this.id = id;
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
