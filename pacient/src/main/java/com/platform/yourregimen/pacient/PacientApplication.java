package com.platform.yourregimen.pacient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class PacientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacientApplication.class, args);
	}

}