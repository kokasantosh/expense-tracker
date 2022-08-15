package com.thirdparty.expensecategorysupplier.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    String transactionPlace;
    String categoryType;

    public Category() {
    }

    public Category(String transactionPlace, String category) {
        this.transactionPlace = transactionPlace;
        this.categoryType = category;
    }

    public String getTransactionPlace() {
        return transactionPlace;
    }

    public void setTransactionPlace(String transactionPlace) {
        this.transactionPlace = transactionPlace;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "transactionPlace='" + transactionPlace + '\'' +
                ", category='" + categoryType + '\'' +
                '}';
    }

}
