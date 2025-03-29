package com.tabcorp.transactionservice.util;

import com.tabcorp.transactionservice.dto.TransactionDto;
import com.tabcorp.transactionservice.model.Product;
import com.tabcorp.transactionservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

@Component
public class TotalCostValidator implements ConstraintValidator<ValidTransactionCost, TransactionDto> {

    @Autowired
    private ProductRepository productRepository;  // Inject ProductRepository to fetch product cost

    @Override
    public void initialize(ValidTransactionCost constraintAnnotation) {
        // You can put initialization logic here if needed.
    }

    @Override
    public boolean isValid(TransactionDto transactionDto, ConstraintValidatorContext context) {
        if (transactionDto == null) {
            return true; // Handle null case
        }

        // Fetch the product cost from the repository
        Optional<Product> product = productRepository.findById(transactionDto.getProductCode());

        if(!product.isPresent()) {
            return false;
        }

        Integer productCost =  product.get().getCost();

        if (productCost == null) {
            return false;  // If product cost is not found, validation fails.
        }

        // Calculate total cost (quantity * product cost)
        double totalCost = transactionDto.getQuantity() * productCost;

        // Validate the total cost is less than or equal to 5000
        return totalCost <= 5000;
    }
}

