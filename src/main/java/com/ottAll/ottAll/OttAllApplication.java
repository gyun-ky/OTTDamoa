package com.ottAll.ottAll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OttAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OttAllApplication.class, args);
	}

}
