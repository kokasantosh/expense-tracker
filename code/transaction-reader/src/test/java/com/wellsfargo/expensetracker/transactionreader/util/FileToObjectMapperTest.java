package com.wellsfargo.expensetracker.transactionreader.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FileToObjectMapperTest {

    @Test
    void testGetTransactionListFromString() throws IOException {
        Optional<List<Transaction>> transactionList = FileToObjectMapper.getTransactionListFromString(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"));
        assertNotNull(transactionList);
    }

    @Test
    void testGetTransactionListFromStringException() throws IOException {
        JsonParseException jsonParseException = assertThrows(JsonParseException.class,
                () -> FileToObjectMapper.getTransactionListFromString(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test_error.json")));
        assertNotNull(jsonParseException);
    }
}