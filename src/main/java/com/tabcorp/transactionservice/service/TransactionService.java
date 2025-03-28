package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.mapper.TransactionMapper;
import com.tabcorp.transactionservice.model.Transaction;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @PersistenceContext
    private EntityManager entityManager;  // Injecting the EntityManager

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    // Save a new transaction
    @Transactional
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction); // Convert to DTO before returning
    }

    // Get all transactions
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toDto) // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    @Transactional
    public void insertBulkTransactions(List<TransactionDto> transactionDtos) {

        int batchSize = 1000;  // Number of transactions per batch

        for (int i = 0; i < transactionDtos.size(); i++) {
            Transaction transaction = transactionMapper.toEntity(transactionDtos.get(i));
            transactionRepository.save(transaction);

            // Execute batch insert when we reach the batch size
            if (i > 0 && i % batchSize == 0) {
                // Flush and clear the session to ensure batch processing
                flushAndClear();
            }
        }
        // Ensure any remaining transactions are saved
        flushAndClear();
    }

    private void flushAndClear() {
        transactionRepository.flush();  // Ensure that the current transactions are written to DB
        entityManager.clear();  // Clear the persistence context to prevent memory overload
    }

}
