package com.example.expensetracker.expensesegregate.request.listener;

import com.example.expensetracker.expensesegregate.domain.Transaction;
import com.example.expensetracker.expensesegregate.domain.TransactionState;
import com.example.expensetracker.expensesegregate.request.feign.IExpenseCategorySupplierProxy;
import com.example.expensetracker.expensesegregate.service.TransactionDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class TxnRequestQueueListener {
    private static final Logger LOG = LoggerFactory.getLogger(TxnRequestQueueListener.class);

    public static final String UNKNOWN = "UNKNOWN";
    @Autowired
    IExpenseCategorySupplierProxy expenseCategorySupplierProxy;
    @Autowired
    TransactionDaoService transactionDaoService;

    /**
     * Get the category and processes the transaction
     * @param transaction
     */
    @KafkaListener(topics = "expense-segregator.req", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
    public void consumeTransaction(Transaction transaction) {
        try {
            AtomicReference<String> category = new AtomicReference<>();
            expenseCategorySupplierProxy.getExpenseCategory(transaction.getTransactionPlace()).ifPresent(category::set);
            transaction.setCategory(category.get().replace("\"", ""));
            transactionDaoService.saveTransaction(transaction, TransactionState.PROCESSED);
            LOG.info("Consumed Transaction: {}", transaction);
        } catch(Exception e) {
            LOG.error("Error while processing the transaction: " + transaction, e);
            transactionDaoService.saveTransaction(transaction, TransactionState.ERROR);
        }
    }

}
