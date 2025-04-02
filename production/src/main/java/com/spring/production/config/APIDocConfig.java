package com.spring.production.config;

import com.spring.production.serviceimpl.SpringServiceImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.*")
public class APIDocConfig {

    private static final Logger logger = LogManager.getLogger(APIDocConfig.class);

    @Bean
    public OpenAPI customOpenAPI() {

        logger.info("Loading Swagger API Documentation Configs...");
        return new OpenAPI()
                .info(new Info().title("Enterprise SpringBoot Application")
                        .version("1.0")
                        .description("A Production Level Spring Boot Application with loaded features")
                        .contact(new Contact().name("Support").email("support@example.com")));
    }
}
