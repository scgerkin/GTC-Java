package com.lesson11.loancalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Entry point for Spring to launch.
 */
@SpringBootApplication
public class LoanCalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanCalculatorApplication.class, args);
    }
}
