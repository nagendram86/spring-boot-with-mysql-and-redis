package com.mysql.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRepoApplication.class, args);
	}

}
