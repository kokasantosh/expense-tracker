package com.example.expensetracker.transactionreader.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileStatus {

    @Id
    private String fileName;
    private String state;

    public FileStatus() {
    }

    public FileStatus(String fileName) {
        this.fileName = fileName;
    }

    public FileStatus(String fileName, String state) {
        this.fileName = fileName;
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FileStatus{" +
                "fileName='" + fileName + '\'' +
                ", status='" + state + '\'' +
                '}';
    }
}
