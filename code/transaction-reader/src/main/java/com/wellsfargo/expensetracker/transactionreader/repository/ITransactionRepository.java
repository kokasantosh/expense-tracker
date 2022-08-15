package com.wellsfargo.expensetracker.transactionreader.repository;

import com.wellsfargo.expensetracker.transactionreader.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findTransactionsByState(String state);
}
