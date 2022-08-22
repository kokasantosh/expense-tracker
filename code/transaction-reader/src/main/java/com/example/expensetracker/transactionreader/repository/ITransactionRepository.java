package com.example.expensetracker.transactionreader.repository;

import com.example.expensetracker.transactionreader.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findTransactionsByState(String state);
}
