package com.wellsfargo.expensetracker.expenseanalyser.feign;

import com.wellsfargo.expensetracker.expenseanalyser.model.CategoryAmount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="expense-segregate")
public interface IExpenseSegregateProxy {

    @GetMapping(value="/expense-segregate/{customerId}", produces = "application/json")
    public List<CategoryAmount> getAllCategoriesWithAmount(@PathVariable Integer customerId);

    @GetMapping(value="/expense-segregate/{customerId}/{category}", produces = "application/json")
    public EntityModel<CategoryAmount> getAmountByCategory(@PathVariable Integer customerId, @PathVariable String category);
}
