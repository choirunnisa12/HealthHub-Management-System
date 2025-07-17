package com.example.healthhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.healthhub.entity")
@EnableJpaRepositories(basePackages = "com.example.healthhub.repository")
@SpringBootApplication
public class HealthHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthHubApplication.class, args);
    }
}
