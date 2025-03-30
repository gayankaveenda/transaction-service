package com.tabcorp.transactionservice.service;


import com.tabcorp.transactionservice.model.Customer;
import com.tabcorp.transactionservice.model.Product;
import com.tabcorp.transactionservice.repository.CustomerRepository;
import com.tabcorp.transactionservice.repository.ProductRepository;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                    new Product("PRODUCT_001", 50),
                    new Product("PRODUCT_002", 100),
                    new Product("PRODUCT_003", 200),
                    new Product("PRODUCT_004", 10),
                    new Product("PRODUCT_005", 500)
            );
            productRepository.saveAll(products);
        }
    }

}
