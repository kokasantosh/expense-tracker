package com.wellsfargo.expensetracker.transactionreader;

import com.wellsfargo.expensetracker.transactionreader.controller.ReplayController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TransactionReaderApplicationTests {
	@Autowired
	ReplayController replayController;
	@Test
	void contextLoads() {
		assertThat(replayController).isNotNull();
	}

}
