package com.santosh.expensetracker.expenseanalyser.controller;

import com.santosh.expensetracker.expenseanalyser.feign.IExpenseSegregateProxy;
import com.santosh.expensetracker.expenseanalyser.model.CategoryAmount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense-analyser")
public class ExpenseAnalyserController {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenseAnalyserController.class);

    @Autowired
    IExpenseSegregateProxy expenseSegregateProxy;

    /**
     * Gets all the sum of all the expenses grouping by categories for a customer
     * @param customerId
     * @return
     */
    @GetMapping(value="/{customerId}", produces = "application/json")
    public List<CategoryAmount> getAllCategoriesWithAmount(@PathVariable Integer customerId) {
        LOG.info("Request received for getting all the categories for amount for customerId: {}", customerId);
        return expenseSegregateProxy.getAllCategoriesWithAmount(customerId);
    }

    /**
     * Gets the sum of expenses for a customer and category
     * @param customerId
     * @param category
     * @return
     */
    @GetMapping(value="/{customerId}/{category}", produces = "application/json")
    public EntityModel<CategoryAmount> getAmountByCategory(@PathVariable Integer customerId, @PathVariable String category) {
        LOG.info("Request received for getting all the categories for amount for customerId: {}", customerId);
        return expenseSegregateProxy.getAmountByCategory(customerId, category);
    }
}
