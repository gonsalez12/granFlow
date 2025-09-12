package com.granaflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class GranaflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(GranaflowApplication.class, args);
	}

}
