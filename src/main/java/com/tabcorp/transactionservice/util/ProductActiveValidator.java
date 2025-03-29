package com.tabcorp.transactionservice.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ProductActiveValidator implements ConstraintValidator<ProductActive, String> {

    @Override
    public boolean isValid(String productStatus, ConstraintValidatorContext context) {
        if (productStatus == null) {
            return false; // Product code cannot be null
        }
        return isProductActive(productStatus); // This is a placeholder logic
    }

    private boolean isProductActive(String productStatus) {
        if (StringUtils.isNotBlank(productStatus)) {
            return productStatus.toLowerCase().equals("active");
        }
        return false;
    }
}
