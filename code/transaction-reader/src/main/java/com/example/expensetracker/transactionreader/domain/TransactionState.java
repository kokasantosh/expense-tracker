package com.example.expensetracker.transactionreader.domain;

public enum TransactionState {
    ERROR("ERROR"),
    PROCESSED("PROCESSED");

    private final String value;
    TransactionState(String v) { this.value = v;}
    public String getValue() {return value;}
}
