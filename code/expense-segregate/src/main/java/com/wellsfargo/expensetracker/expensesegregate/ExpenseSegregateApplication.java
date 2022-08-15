package com.wellsfargo.expensetracker.expensesegregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExpenseSegregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseSegregateApplication.class, args);
	}

}
