package com.example.expensetracker.expensesegregate.domain;

public class CategoryAmount {
    String category;
    Long amount;

    public CategoryAmount() {
    }

    public CategoryAmount(String category) {
        this.category = category;
    }

    public CategoryAmount(String category, Long amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CategoryAmount{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
