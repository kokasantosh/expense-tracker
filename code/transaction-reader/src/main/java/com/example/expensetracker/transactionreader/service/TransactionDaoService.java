package com.example.expensetracker.transactionreader.service;

import com.example.expensetracker.transactionreader.repository.ITransactionRepository;
import com.example.expensetracker.transactionreader.domain.InputSource;
import com.example.expensetracker.transactionreader.domain.TransactionState;
import com.example.expensetracker.transactionreader.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionDaoService {
    @Autowired
    private ITransactionRepository transactionRepository;

    /**
     * Saves the transaction to DB after setting filename, source, transaction state
     * @param transaction
     * @param fileName
     * @param source
     * @param state
     */
    public void saveTransaction(Transaction transaction, String fileName, InputSource source, TransactionState state) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transaction.getTransactionId());
        Transaction transactionToSave = transactionOptional.isPresent() ? transactionOptional.get() : transaction;
        transactionToSave.setFileName(fileName);
        transactionToSave.setSource(source.getValue());
        transactionToSave.setTransactionState(state.getValue());
        saveTransaction(transactionToSave);
    }

    /**
     * Saves the transaction to DB
     * @param transaction
     */
    private void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Gets the error transactions from DB
     * @return
     */
    public List<Transaction> getErrorTransactions() {
       return transactionRepository.findTransactionsByState(TransactionState.ERROR.getValue());
    }
}
