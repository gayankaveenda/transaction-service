package com.tabcorp.transactionservice.controller;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.service.TransactionService;
import com.tabcorp.transactionservice.util.TransactionDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionDataGenerator transactionDataGenerator;


    @Autowired
    public TransactionController(TransactionService transactionService, TransactionDataGenerator transactionDataGenerator) {
        this.transactionService = transactionService;
        this.transactionDataGenerator = transactionDataGenerator;
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        // Convert DTO to entity (done in service) and save it
        TransactionDto savedTransaction = transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to insert bulk transactions
    @PostMapping("/bulk")
    public ResponseEntity<String> insertBulkTransactions(@RequestBody List<TransactionDto> transactionDtos) {
        try {
            transactionService.insertBulkTransactions(transactionDtos);
            return new ResponseEntity<>("Bulk transactions inserted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inserting bulk transactions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bulkInsert")
    public ResponseEntity<String> insertBulkTransactions(@RequestParam int numberOfTransactions) {
        try {
            // Generate the transactions using the generator
            List<TransactionDto> transactionDtos = transactionDataGenerator.generateTransactionDtos(numberOfTransactions);

            // Pass the generated transactions to the service layer for saving
            transactionService.insertBulkTransactions(transactionDtos);

            return new ResponseEntity<>("Bulk transactions inserted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error inserting bulk transactions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
