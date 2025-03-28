package com.tabcorp.transactionservice.mapper;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getTransactionTime(),
                transaction.getCustomerId(),
                transaction.getQuantity(),
                transaction.getProductCode()
        );
    }

    public Transaction toEntity(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(dto.getTransactionTime());
        transaction.setCustomerId(dto.getCustomerId());
        transaction.setQuantity(dto.getQuantity());
        transaction.setProductCode(dto.getProductCode());
        return transaction;
    }
}
