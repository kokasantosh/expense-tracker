package com.example.expensetracker.transactionreader.listener;

import com.example.expensetracker.transactionreader.service.TransactionProcessor;
import com.example.expensetracker.transactionreader.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class QueueRequestListener {
    private static final Logger LOG = LoggerFactory.getLogger(QueueRequestListener.class);
    @Autowired
    private TransactionProcessor transactionProcessor;

    /**
     * Processes the Transaction received in KAFKA request topic
     * @param transaction
     */
    @KafkaListener(topics = "transaction-reader.req", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
    public void consumeTransaction(Transaction transaction) {
        try {
            transactionProcessor.processTransaction(transaction, null);
        } catch(Exception e) {
            LOG.error("Error while processing the transaction: {}", transaction, e);
        }
    }

}
