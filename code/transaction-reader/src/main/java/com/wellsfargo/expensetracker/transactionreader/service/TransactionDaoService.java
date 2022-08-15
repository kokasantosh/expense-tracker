package com.wellsfargo.expensetracker.transactionreader.service;

import com.wellsfargo.expensetracker.transactionreader.repository.ITransactionRepository;
import com.wellsfargo.expensetracker.transactionreader.domain.InputSource;
import com.wellsfargo.expensetracker.transactionreader.domain.TransactionState;
import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        transaction.setFileName(fileName);
        transaction.setSource(source.getValue());
        transaction.setTransactionState(state.getValue());
        saveTransaction(transaction);
    }

    /**
     * Saves the transaction to DB
     * @param transaction
     */
    public void saveTransaction(Transaction transaction) {
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
