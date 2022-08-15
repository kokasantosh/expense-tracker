package com.wellsfargo.expensetracker.transactionreader.util;

import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class ValidationUtilsTest {

    @Test
    void testValidateException() {
        Transaction transaction = new Transaction();
        ConstraintViolationException constraintViolationException = assertThrows(ConstraintViolationException.class,
                () -> ValidationUtils.validate(transaction));
        assertNotNull(constraintViolationException);
    }
}