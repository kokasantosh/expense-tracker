package com.example.expensetracker.transactionreader.exception;

public class TransactionProcessingException extends RuntimeException{

    public TransactionProcessingException(String message) {
        super(message);
    }
}
