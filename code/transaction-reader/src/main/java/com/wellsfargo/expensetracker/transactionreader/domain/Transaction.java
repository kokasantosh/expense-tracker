package com.wellsfargo.expensetracker.transactionreader.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    private Integer transactionId;
    @NotNull
    private Integer customerId;
    @NotBlank
    private String customerName;
    @NotNull
    private Long amount;
    @NotBlank
    private String transactionPlace;
    @NotNull
    private Date date;
    private String fileName;
    private String source;
    private String state;

    public Transaction() {
        super();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransactionState() {
        return state;
    }

    public void setTransactionState(String state) {
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
                ", date='" + date + '\'' +
                ", fileName='" + fileName + '\'' +
                ", source='" + source + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
