package com.example.puskesmas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PuskesmasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuskesmasApplication.class, args);
	}

}
