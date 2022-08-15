package com.thirdparty.expensecategorysupplier.controller;

import com.thirdparty.expensecategorysupplier.repository.ICategoryRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ExpenseCategorySupplyController {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenseCategorySupplyController.class);

    public static final String UNKNOWN = "UNKNOWN";

    @Autowired
    ICategoryRepository categoryRepository;

    /**
     * Returns the category based on the transaction place
     * @param transactionPlace
     * @return
     */
    @CircuitBreaker(name="default", fallbackMethod = "getFallBackResponse")
    @GetMapping("/expense-category-supplier/{transactionPlace}")
    public Optional<String> getExpenseCategory(@PathVariable String transactionPlace) {
        LOG.info("Getting the category for transaction place: {}", transactionPlace);
        AtomicReference<String> category = new AtomicReference<>(UNKNOWN);
        categoryRepository.findById(transactionPlace).ifPresent(obj -> category.set(obj.getCategoryType()));
        LOG.info("Successfully Returning the category {} for transaction place: {}", category.get(), transactionPlace);
        return Optional.of(category.get());
    }

    /**
     * Returns the fallback incase the actual method fails to do so
     * @param ex
     * @return
     */
    public Optional<String> getFallBackResponse(Exception ex) {
        LOG.info("Going through fallback response due to exception: ", ex);
        return Optional.of(UNKNOWN);
    }
}
