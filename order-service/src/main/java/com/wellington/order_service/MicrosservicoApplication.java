package com.wellington.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.wellington.order_service.repository")
public class MicrosservicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosservicoApplication.class, args);
	}

}
