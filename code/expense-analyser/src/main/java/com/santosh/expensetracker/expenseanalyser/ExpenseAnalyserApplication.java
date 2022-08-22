package com.santosh.expensetracker.expenseanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExpenseAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseAnalyserApplication.class, args);
	}

}
