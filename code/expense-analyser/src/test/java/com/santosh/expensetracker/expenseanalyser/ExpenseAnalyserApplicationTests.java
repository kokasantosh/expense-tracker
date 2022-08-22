package com.santosh.expensetracker.expenseanalyser;

import com.santosh.expensetracker.expenseanalyser.controller.ExpenseAnalyserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ExpenseAnalyserApplicationTests {

	@Autowired
	private ExpenseAnalyserController expenseAnalyserController;

	@Test
	void contextLoads() {
		assertThat(expenseAnalyserController).isNotNull();
	}

}
