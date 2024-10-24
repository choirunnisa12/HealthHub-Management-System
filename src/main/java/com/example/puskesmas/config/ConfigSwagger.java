package com.example.puskesmas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Puskesmas API")
                        .version("1.0")
                        .description("API documentation for puskesmas system")
                        .contact(new Contact()
                                .name("choirunnisa")
                                .email("choirunnisaa32@gmail.com")
                                .url("https://choirunnisa12.github.io/portfolio/")
                        )
                )
                .servers(List.of(
                        new Server().url("http:localhost:8008").description("Local Server")
                ));
    }
}
