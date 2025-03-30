package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.mapper.TransactionMapper;
import com.tabcorp.transactionservice.model.Transaction;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;


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

    public Page<TransactionDto> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactionsPage = transactionRepository.findAll(pageable);
        return transactionsPage.map(transactionMapper::toDto); // Convert each entity to DTO
    }

    @Transactional
    public void insertBulkTransactions(List<TransactionDto> transactionDtos) {
        logger.info("Fetched Batch Size: {}", batchSize);

        if (transactionDtos.isEmpty()) {
            logger.warn("No transactions to insert.");
            return;
        }

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, (int) Math.ceil((double) transactionDtos.size() / getBatchSize()))
                    .forEach(batchNumber -> executor.execute(() -> processBatch(batchNumber, batchSize, transactionDtos)));

            logger.info("All batches submitted for processing.");
        }
    }

    private void processBatch(int batchNumber, int batchSize, List<TransactionDto> transactionDtos) {
        int fromIndex = batchNumber * batchSize;
        int toIndex = Math.min(fromIndex + batchSize, transactionDtos.size());

        List<Transaction> transactions = transactionDtos.subList(fromIndex, toIndex)
                .stream()
                .map(transactionMapper::toEntity)
                .toList();

        transactionRepository.saveAll(transactions);
        flushAndClear();

        logger.info("Batch {} processed: {} transactions", batchNumber + 1, transactions.size());
    }


    private void flushAndClear() {
        transactionRepository.flush();  // Ensure that the current transactions are written to DB
        entityManager.clear();  // Clear the persistence context to prevent memory overload
    }

    public int getBatchSize() {
        return batchSize;
    }

}
