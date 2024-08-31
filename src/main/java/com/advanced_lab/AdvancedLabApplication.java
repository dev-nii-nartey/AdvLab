package com.advanced_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AdvancedLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedLabApplication.class, args);
	}

}
