package com.thirdparty.expensecategorysupplier;

import com.thirdparty.expensecategorysupplier.controller.ExpenseCategorySupplyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ExpenseCategorySupplierApplicationTests {

	@Autowired
	private ExpenseCategorySupplyController expenseCategorySupplyController;
	@Test
	void contextLoads() {
		assertThat(expenseCategorySupplyController).isNotNull();
	}

}
