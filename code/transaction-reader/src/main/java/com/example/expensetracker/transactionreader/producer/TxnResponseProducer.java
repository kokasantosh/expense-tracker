package com.example.expensetracker.transactionreader.producer;

import com.example.expensetracker.transactionreader.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TxnResponseProducer {

    @Autowired
    private KafkaTemplate<String, Transaction> transactionKafkaTemplate;
    @Value("${response.topic}")
    private String topic;

    public void sendResponse(Transaction transaction) {
        transactionKafkaTemplate.send(topic, transaction);
    }
}
