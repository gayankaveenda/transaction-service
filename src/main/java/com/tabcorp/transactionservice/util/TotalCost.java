package com.tabcorp.transactionservice.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TotalCostValidator.class)
public @interface TotalCost {
    String message() default "Total cost of transaction must not exceed 5000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
