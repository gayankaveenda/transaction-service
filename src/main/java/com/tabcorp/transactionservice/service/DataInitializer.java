package com.tabcorp.transactionservice.service;


import com.tabcorp.transactionservice.model.Customer;
import com.tabcorp.transactionservice.model.Product;
import com.tabcorp.transactionservice.model.Transaction;
import com.tabcorp.transactionservice.repository.CustomerRepository;
import com.tabcorp.transactionservice.repository.ProductRepository;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    public DataInitializer(CustomerRepository customerRepository,
                           ProductRepository productRepository,
                           TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) {
        loadCustomers();
        loadProducts();
//        loadTransactions();
    }

    private void loadCustomers() {
        if (customerRepository.count() == 0) { // Avoid duplicates
            List<Customer> customers = List.of(
                    new Customer(10001L, "Tony", "Stark", "tony.stark@gmail.com", "Australia"),
                    new Customer(10002L, "Bruce", "Banner", "bruce.banner@gmail.com", "US"),
                    new Customer(10003L, "Steve", "Rogers", "steve.rogers@hotmail.com", "Australia"),
                    new Customer(10004L, "Wanda", "Maximoff", "wanda.maximoff@gmail.com", "US"),
                    new Customer(10005L, "Natasha", "Romanoff", "natasha.romanoff@gmail.com", "Canada")
            );
            customerRepository.saveAll(customers);
        }
    }

    private void loadProducts() {
        if (productRepository.count() == 0) { // Avoid duplicates
            List<Product> products = List.of(
                    new Product("PRODUCT_001", 50, "Active"),
                    new Product("PRODUCT_002", 100, "Inactive"),
                    new Product("PRODUCT_003", 200, "Active"),
                    new Product("PRODUCT_004", 10, "Inactive"),
                    new Product("PRODUCT_005", 500, "Active")
            );
            productRepository.saveAll(products);
        }
    }

    private void loadTransactions() {
        if (transactionRepository.count() == 0) { // Avoid duplicates
            List<Transaction> transactions = List.of(
                    new Transaction(LocalDateTime.now(), 10001L, 2, "PRODUCT_001"),
                    new Transaction(LocalDateTime.now().minusHours(1), 10002L, 5, "PRODUCT_003"),
                    new Transaction(LocalDateTime.now().minusDays(1), 10003L, 1, "PRODUCT_005"),
                    new Transaction(LocalDateTime.now().minusDays(2), 10004L, 10, "PRODUCT_002"),
                    new Transaction(LocalDateTime.now().minusHours(4), 10005L, 3, "PRODUCT_004")
            );
            transactionRepository.saveAll(transactions);
        }
    }
}
