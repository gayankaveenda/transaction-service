package com.tabcorp.transactionservice.util;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TotalCostValidator implements ConstraintValidator<TotalCost, Long> {

    private static final double MAX_COST = 5000.0;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // skip validation if null
        }
        return value <= MAX_COST;
    }
}
