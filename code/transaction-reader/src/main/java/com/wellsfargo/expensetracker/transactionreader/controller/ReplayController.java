package com.wellsfargo.expensetracker.transactionreader.controller;

import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import com.wellsfargo.expensetracker.transactionreader.service.FileStatusDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionDaoService;
import com.wellsfargo.expensetracker.transactionreader.service.TransactionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/transaction-reader")
public class ReplayController {
    private static final Logger LOG = LoggerFactory.getLogger(ReplayController.class);

    @Autowired
    TransactionProcessor transactionProcessor;
    @Autowired
    FileStatusDaoService fileStatusDaoService;
    @Autowired
    TransactionDaoService transactionDaoService;

    /**
     * Processes the file based on the file path provided
     * @param filePath
     * @return
     */
    @GetMapping("/{filePath}")
    public String getReplay(@PathVariable String  filePath) {
        try {
            File file = new File(filePath);
            transactionProcessor.processFile(file);
            LOG.info("Processed the file successfully: {}", file.getName());
            return "Successfully Replayed";
        } catch(Exception e) {
            LOG.error("Error while processing the file: " + filePath, e);
            return "Error while replaying the file: " + filePath;
        }
    }

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
