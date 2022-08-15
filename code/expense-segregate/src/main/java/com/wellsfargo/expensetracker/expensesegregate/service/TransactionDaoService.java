package com.wellsfargo.expensetracker.expensesegregate.service;

import com.wellsfargo.expensetracker.expensesegregate.domain.CategoryAmount;
import com.wellsfargo.expensetracker.expensesegregate.repository.ITransactionRepository;
import com.wellsfargo.expensetracker.expensesegregate.domain.TransactionState;
import com.wellsfargo.expensetracker.expensesegregate.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionDaoService {
    @Autowired
    private ITransactionRepository transactionRepository;

    /**
     * Saves the transaction after setting the state of the transaction
     * @param transaction
     * @param state
     */
    public void saveTransaction(Transaction transaction, TransactionState state) {
        transaction.setState(state.getValue());
        saveTransaction(transaction);
    }

    /**
     * Saves the transaction
     * @param transaction
     */
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Get expense of all categories for a customer
     * @param customerId
     * @return
     */
    public List<CategoryAmount> getAllCategoriesWithAmount(Integer customerId) {
        List<Transaction> transactionList = transactionRepository.findTransactionsByCustomerId(customerId);
        List<CategoryAmount> categoryAmounts = new ArrayList<>();
        transactionList.stream().collect(Collectors.groupingBy(Transaction::getCategory,
                Collectors.summingLong(Transaction::getAmount)))
                .forEach((category, amountSum) -> categoryAmounts.add(new CategoryAmount(category, amountSum)));
        return categoryAmounts;
    }

    /**
     * Gets the expepens of the customer for a transaction
     * @param customerId
     * @param category
     * @return
     */
    public CategoryAmount getAmountWithCategory(Integer customerId, String category) {
        List<Transaction> transactionList = transactionRepository.findTransactionsByCustomerIdAndCategory(customerId, category);
        CategoryAmount categoryAmount = new CategoryAmount(category);
        categoryAmount.setAmount(transactionList.stream().mapToLong(Transaction::getAmount).sum());
        return categoryAmount;
    }


}
