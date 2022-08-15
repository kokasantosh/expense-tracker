package com.wellsfargo.expensetracker.expensesegregate;

import com.wellsfargo.expensetracker.expensesegregate.controller.ExpenseSegregateController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ExpenseSegregateApplicationTests {

	@Autowired
	private ExpenseSegregateController expenseSegregateController;

	@Test
	void contextLoads() {
		assertThat(expenseSegregateController).isNotNull();
	}

}
