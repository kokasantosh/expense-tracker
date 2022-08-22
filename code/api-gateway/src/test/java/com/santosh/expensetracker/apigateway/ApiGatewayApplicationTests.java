package com.santosh.expensetracker.apigateway;

import com.santosh.expensetracker.apigateway.configuration.ApiGatewayConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiGatewayApplicationTests {

	@Autowired
	private ApiGatewayConfiguration apiGatewayConfiguration;
	@Test
	void contextLoads() {
		assertThat(apiGatewayConfiguration).isNotNull();
	}

}
