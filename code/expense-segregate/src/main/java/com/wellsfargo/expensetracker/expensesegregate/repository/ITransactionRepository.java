package com.wellsfargo.expensetracker.expensesegregate.repository;

import com.wellsfargo.expensetracker.expensesegregate.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findTransactionsByCustomerId(Integer customerId);

    public List<Transaction> findTransactionsByCustomerIdAndCategory(Integer customerId, String category);
}
