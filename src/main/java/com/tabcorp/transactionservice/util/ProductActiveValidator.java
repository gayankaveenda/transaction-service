package com.tabcorp.transactionservice.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ProductActiveValidator implements ConstraintValidator<ProductActive, String> {

    @Override
    public boolean isValid(String productCode, ConstraintValidatorContext context) {
        if (productCode == null) {
            return false; // Product code cannot be null
        }
        boolean isActive = isProductActive(productCode); // This is a placeholder logic
        return isActive;
    }

    private boolean isProductActive(String productCode) {
        if (StringUtils.isNotBlank(productCode)) {
            return productCode.toLowerCase().contains("active");
        }
        return false;
    }
}
