package com.tabcorp.transactionservice.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductActiveValidator.class)
public @interface ProductActive {
    String message() default "Product must be active";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
