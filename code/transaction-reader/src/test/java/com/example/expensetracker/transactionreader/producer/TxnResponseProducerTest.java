package com.example.expensetracker.transactionreader.producer;

import com.example.expensetracker.transactionreader.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TxnResponseProducerTest {

    @Mock
    private KafkaTemplate<String, Transaction> transactionKafkaTemplate;

    @InjectMocks
    private TxnResponseProducer txnResponseProducer;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendResponse() {
        txnResponseProducer.sendResponse(new Transaction());
        verify(transactionKafkaTemplate, times(1)).send(any(), any());
    }
}