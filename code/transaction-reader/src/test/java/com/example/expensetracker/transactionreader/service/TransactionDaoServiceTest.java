package com.example.expensetracker.transactionreader.service;

import com.example.expensetracker.transactionreader.domain.InputSource;
import com.example.expensetracker.transactionreader.domain.Transaction;
import com.example.expensetracker.transactionreader.domain.TransactionState;
import com.example.expensetracker.transactionreader.repository.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class TransactionDaoServiceTest {

    @Mock
    ITransactionRepository transactionRepository;

    @InjectMocks
    TransactionDaoService transactionDaoService;

    @Test
    void testSaveTransaction1() {
        transactionDaoService.saveTransaction(new Transaction(), "", InputSource.FILE, TransactionState.PROCESSED);
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void getErrorTransactions() {
        transactionDaoService.getErrorTransactions();
        verify(transactionRepository, times(1)).findTransactionsByState(any());
    }
}