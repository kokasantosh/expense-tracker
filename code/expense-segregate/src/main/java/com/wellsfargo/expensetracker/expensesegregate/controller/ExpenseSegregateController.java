package com.wellsfargo.expensetracker.expensesegregate.controller;

import com.wellsfargo.expensetracker.expensesegregate.domain.CategoryAmount;
import com.wellsfargo.expensetracker.expensesegregate.service.TransactionDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense-segregate")
public class ExpenseSegregateController {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenseSegregateController.class);
    @Autowired
    TransactionDaoService transactionDaoService;

    /**
     * Gets all the sum of all the expenses grouping by categories for a customer
     * @param customerId
     * @return
     */
    @GetMapping(value="/{customerId}", produces = "application/json")
    public List<CategoryAmount> getAllCategoriesWithAmount(@PathVariable Integer customerId) {
        LOG.info("Request received for getting all the categories for amount for customerId: {}", customerId);
        List<CategoryAmount> categoryAmountList = transactionDaoService.getAllCategoriesWithAmount(customerId);
        LOG.info("Successfully processed Request for: {}", customerId);
        return categoryAmountList;
    }

    /**
     * Gets the sum of expenses for a customer and category
     * @param customerId
     * @param category
     * @return
     */
    @GetMapping(value="/{customerId}/{category}", produces = "application/json")
    public EntityModel<CategoryAmount> getAmountByCategory(@PathVariable Integer customerId, @PathVariable String category) {
        LOG.info("Request received for getting all the category: {} for amount for customerId: {}", category, customerId);
        CategoryAmount categoryAmount = transactionDaoService.getAmountWithCategory(customerId, category);
        EntityModel<CategoryAmount> model = EntityModel.of(categoryAmount);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllCategoriesWithAmount(customerId));
        model.add(linkBuilder.withRel("Amount-By-All-categories"));
        LOG.info("Successfully processed Request for: {} for category: {}", customerId, category);
        return model;
    }
}
