package com.example.lctc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LctcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LctcApplication.class, args);
	}

}
