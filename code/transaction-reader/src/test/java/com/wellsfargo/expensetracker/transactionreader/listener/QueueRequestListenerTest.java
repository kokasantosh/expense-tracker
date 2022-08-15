package com.wellsfargo.expensetracker.transactionreader.listener;

import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import com.wellsfargo.expensetracker.transactionreader.exception.TransactionProcessingException;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class QueueRequestListenerTest {

    @Mock
    private TransactionProcessor transactionProcessor;

    @InjectMocks
    private QueueRequestListener queueRequestListener;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsumeTransaction() {
        queueRequestListener.consumeTransaction(new Transaction());
        verify(transactionProcessor, times(1)).processTransaction(any(), any());
    }

    @Test
    void testConsumeTransactionException() {
        doThrow(TransactionProcessingException.class)
                .when(transactionProcessor)
                .processTransaction(any(), any());
        queueRequestListener.consumeTransaction(new Transaction());
        verify(transactionProcessor, times(1)).processTransaction(any(), any());
    }
}