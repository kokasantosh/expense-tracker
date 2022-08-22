package com.example.expensetracker.transactionreader.util;

import com.example.expensetracker.transactionreader.domain.Transaction;

import javax.validation.*;
import java.util.Set;

public final class ValidationUtils {
    private static final Validator VALIDATOR;

    private ValidationUtils() {}

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static void validate(Transaction transaction, Class<?>... groups) {
        Set<ConstraintViolation<Transaction>> result = VALIDATOR.validate(transaction, groups);

        if(!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }
    }

    public static boolean isValid(Transaction transaction, Class<?>... groups) {
        Set<ConstraintViolation<Transaction>> result = VALIDATOR.validate(transaction, groups);
        return result.isEmpty();
    }
}
