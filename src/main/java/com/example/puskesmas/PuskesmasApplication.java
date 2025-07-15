package com.example.puskesmas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableCaching
@EntityScan("com.example.puskesmas.entity")
@EnableAsync
@EnableJpaRepositories(basePackages = "com.example.puskesmas.repository") // pastikan sesuai

public class PuskesmasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuskesmasApplication.class, args);
	}

	@Bean
public CommandLineRunner demo(EntityManagerFactory emf) {
    return args -> {
        System.out.println("Entities: " + emf.getMetamodel().getEntities());
    };
}

}
