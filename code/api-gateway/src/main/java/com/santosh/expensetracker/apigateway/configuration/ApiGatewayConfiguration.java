package com.santosh.expensetracker.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/transaction-reader/**")
                        .uri("lb://transaction-reader"))
                .route(p -> p.path("/expense-category-supplier/**")
                        .uri("lb://expense-category-supplier"))
                .route(p -> p.path("/expense-segregate/**")
                        .uri("lb://expense-segregate"))
                .route(p -> p.path("/expense-analyser/**")
                        .uri("lb://expense-analyser"))
                .build();
    }
}
