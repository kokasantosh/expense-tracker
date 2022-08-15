package com.wellsfargo.expensetracker.transactionreader.domain;

public enum InputSource {
    FILE("FILE"),
    QUEUE("QUEUE");
    private final String value;
    InputSource(String v) { this.value = v;}
    public String getValue() {return value;}
}
