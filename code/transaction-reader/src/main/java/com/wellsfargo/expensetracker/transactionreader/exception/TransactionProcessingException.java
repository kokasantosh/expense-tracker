package com.wellsfargo.expensetracker.transactionreader.exception;

public class TransactionProcessingException extends RuntimeException{

    public TransactionProcessingException(String message) {
        super(message);
    }
}
