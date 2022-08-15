package com.wellsfargo.expensetracker.expensesegregate.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    private Integer transactionId;
    private Integer customerId;
    private String customerName;
    private Long amount;
    private String transactionPlace;
    private String category;
    private Date date;
    private String state;

    public Transaction() {
    }

    public Transaction(Integer transactionId, Integer customerId, String customerName, Long amount, String transactionPlace, String category, Date date, String state) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.amount = amount;
        this.transactionPlace = transactionPlace;
        this.category = category;
        this.date = date;
        this.state = state;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionPlace() {
        return transactionPlace;
    }

    public void setTransactionPlace(String transactionPlace) {
        this.transactionPlace = transactionPlace;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", transactionPlace='" + transactionPlace + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }
}
