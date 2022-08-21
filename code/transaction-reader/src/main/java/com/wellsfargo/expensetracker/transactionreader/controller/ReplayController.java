package com.wellsfargo.expensetracker.transactionreader.controller;

import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import com.wellsfargo.expensetracker.transactionreader.service.FileStatusDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction-reader")
public class ReplayController {
    @Autowired
    FileStatusDaoService fileStatusDaoService;
    @Autowired
    TransactionDaoService transactionDaoService;

    /**
     * Gets the Error'ed files from DB, so that user can replay them if needed after correcting
     * @return
     */
    @GetMapping("/getErrorFiles")
    public List<FileStatus> getErrorFiles() {
        return fileStatusDaoService.getErrorFiles();
    }

    /**
     * Gets the Error'ed transactions from DB, so that user can replay them if needed
     * @return
     */
    @GetMapping("/getErrorTransaction")
    public List<Transaction> getErrorTransactions() {
        return transactionDaoService.getErrorTransactions();
    }
}
