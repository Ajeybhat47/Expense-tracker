package com.expensetracker.workms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WorkmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkmsApplication.class, args);
	}
}
