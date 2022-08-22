package com.example.expensetracker.transactionreader.service;

import com.example.expensetracker.transactionreader.domain.Transaction;
import com.example.expensetracker.transactionreader.producer.TxnResponseProducer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties ")
class TransactionProcessorTest {

    @Value("${folder.processed}")
    private String processedFolderPath;
    @Value("${folder.error}")
    private String errorFolderPath;

    @Mock
    private TransactionDaoService transactionDaoService;
    @Mock
    private FileStatusDaoService fileStatusDaoService;
    @Mock
    private TxnResponseProducer responseProducer;

    @InjectMocks
    TransactionProcessor transactionProcessor;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(transactionProcessor, "processedFolderPath", processedFolderPath);
        ReflectionTestUtils.setField(transactionProcessor, "errorFolderPath", errorFolderPath);
    }

    @Test
    void testProcessChangedFile() throws IOException {
        ChangedFile file = new ChangedFile(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"), ChangedFile.Type.ADD);
        transactionProcessor.processChangedFile(file);
        verify(fileStatusDaoService, times(1)).saveFileStatus(any(), any());
        Files.move(Paths.get("C:\\personnal\\workspace\\Wells\\filequeue\\processed\\sample_test.json"), Paths.get("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test.json"));
    }

    @Test
    void testProcessChangedFileException() throws IOException {
        ChangedFile file = new ChangedFile(new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles"), new File("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test_error.json"), ChangedFile.Type.ADD);
        Exception exception = assertThrows(Exception.class,
                () -> transactionProcessor.processChangedFile(file));
        assertNotNull(exception);
        Files.move(Paths.get("C:\\personnal\\workspace\\Wells\\filequeue\\error\\sample_test_error.json"), Paths.get("C:\\personnal\\workspace\\Wells\\code\\transaction-reader\\src\\test\\testfiles\\sample_test_error.json"));
    }

    @Test
    void testValidateTransactionException() {
        Transaction transaction = new Transaction();
        transactionProcessor.validateTransaction(transaction);
        doNothing();
    }

}