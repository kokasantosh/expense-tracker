package com.wellsfargo.expensetracker.expensesegregate.request.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="expense-category-supplier")
public interface IExpenseCategorySupplierProxy {

    @GetMapping("/expense-category-supplier/{transactionPlace}")
    public Optional<String> getExpenseCategory(@PathVariable String transactionPlace);

}
