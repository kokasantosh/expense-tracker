package com.wellsfargo.expensetracker.transactionreader.service;

import com.wellsfargo.expensetracker.transactionreader.domain.InputSource;
import com.wellsfargo.expensetracker.transactionreader.domain.TransactionState;
import com.wellsfargo.expensetracker.transactionreader.exception.TransactionProcessingException;
import com.wellsfargo.expensetracker.transactionreader.domain.FileStatus;
import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import com.wellsfargo.expensetracker.transactionreader.producer.TxnResponseProducer;
import com.wellsfargo.expensetracker.transactionreader.util.FileToObjectMapper;
import com.wellsfargo.expensetracker.transactionreader.util.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionProcessor.class);
    @Autowired
    private TransactionDaoService transactionDaoService;
    @Autowired
    private FileStatusDaoService fileStatusDaoService;
    @Autowired
    private TxnResponseProducer responseProducer;
    @Value("${folder.processed}")
    private String processedFolderPath;
    @Value("${folder.error}")
    private String errorFolderPath;

    /**
     * Processes the Changed file
     * @param file
     * @throws Exception
     */
    @Transactional
    public void processChangedFile(ChangedFile file) throws IOException{
        if(!isLocked(file.getFile().toPath())) {
            processFile(file.getFile());
        }
    }

    /**
     * Processes the file
     * @param file
     * @throws Exception
     */
    @Transactional
    public void processFile(File file) throws IOException{
        FileStatus fileStatus = new FileStatus(file.getName());
        try {
            LOG.info("Processing the file: {}  is IN-PROGRESS", file.getName());
            Optional<List<Transaction>> transactionOptional = FileToObjectMapper.getTransactionListFromString(file);

            List<Transaction> transactions = transactionOptional.isPresent() ? transactionOptional.get() : new ArrayList<>();

            //Validates the transactions
            transactions.forEach(this::validateTransaction);

            //Processes each transaction in the list
            transactions.forEach(txn -> {
                if(ValidationUtils.isValid(txn))
                    processTransaction(txn, file.getName());});

            //Move the file from input folder to processed folder
            Files.move(Paths.get(file.getAbsolutePath()),
                            Paths.get(processedFolderPath + file.getName()));
            LOG.info("Moved the file: {} to processed folder", file.getName());

            fileStatusDaoService.saveFileStatus(fileStatus, TransactionState.PROCESSED);
            LOG.info("Processing the file: {} is SUCCESSFUL", file.getName());
        } catch (IOException e) {
            //TODO: If error is retryval exceptions then retry for n number of times before exiting
            LOG.error("Error while processing the file: {} : ", file.getName());
            //Move the file from input folder to Error folder
            Files.move(Paths.get(file.getAbsolutePath()),
                    Paths.get(errorFolderPath + file.getName()));
            LOG.info("Moved the file: {} to error folder", file.getName());
            fileStatusDaoService.saveFileStatus(fileStatus, TransactionState.ERROR);
            throw e;
        }
    }

    @Transactional
    public void validateTransaction(Transaction transaction) {
        try {
            ValidationUtils.validate(transaction);
        } catch(ConstraintViolationException e) {
            LOG.error("Validation failed for transaction: {}", transaction.getTransactionId(), e);
            //TODO: Save Error transactions to the error folder from where user can be picked up, corrected and replay
        }
    }
    /**
     * Processes the transaction
     * @param transaction
     * @param fileName
     * @throws Exception
     */
    @Transactional
    public void processTransaction(Transaction transaction, String fileName) throws TransactionProcessingException{
        try {
            LOG.info("Sending the response to Kafta response topic: {}", transaction.getTransactionId());
            //Sending the response teo the response Kafka topic
            responseProducer.sendResponse(transaction);
            transactionDaoService.saveTransaction(transaction, fileName, StringUtils.isEmpty(fileName) ? InputSource.QUEUE : InputSource.FILE, TransactionState.PROCESSED);
            LOG.info("Successfully processed the transaction: {}", transaction.getTransactionId());
        } catch(TransactionProcessingException e) {
            LOG.error("Error while processing the transaction: {}", transaction.getTransactionId());
            transactionDaoService.saveTransaction(transaction, fileName, StringUtils.isEmpty(fileName) ? InputSource.QUEUE : InputSource.FILE, TransactionState.ERROR);
        }
    }

    /*
       Checks if the file is already locked and provides a lock for file to process
     */
    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }
}
