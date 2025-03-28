package com.tabcorp.transactionservice.util;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.model.Customer;
import com.tabcorp.transactionservice.model.Product;
import com.tabcorp.transactionservice.repository.CustomerRepository;
import com.tabcorp.transactionservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TransactionDataGenerator {

    private final Random random = new Random();
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TransactionDataGenerator(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<TransactionDto> generateTransactionDtos(int numberOfTransactions) {
        List<TransactionDto> transactionDtos = new ArrayList<>();

        // Get all customers and products from the repositories
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();

        // Extract customer IDs and product codes into separate lists
        List<Long> customerIds = new ArrayList<>();
        List<String> productCodes = new ArrayList<>();

        for (Customer customer : customers) {
            customerIds.add(customer.getId());
        }

        for (Product product : products) {
            productCodes.add(product.getProductCode());
        }

        // Generate transactions
        for (int i = 0; i < numberOfTransactions; i++) {
            // Randomly select a customer ID from the customer IDs list
            Long randomCustomerId = customerIds.get(random.nextInt(customerIds.size()));

            // Randomly select a product code from the product codes list
            String randomProductCode = productCodes.get(random.nextInt(productCodes.size()));

            // Generate a random quantity
            int quantity = random.nextInt(10) + 1;  // Random quantity between 1 and 10

            // Get current timestamp for the transaction
            LocalDateTime transactionTime = LocalDateTime.now();

            // Create the TransactionDto with appropriate values
            TransactionDto transactionDto = new TransactionDto(
                    transactionTime,
                    randomCustomerId,  // customerId from the customer list
                    quantity,  // quantity
                    randomProductCode  // productCode from the product list
            );
            transactionDtos.add(transactionDto);
        }

        return transactionDtos;
    }
}

