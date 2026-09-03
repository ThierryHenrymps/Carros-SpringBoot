package com.api.car.carros.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Carros",
                version = "1.0",
                description = "Documentação da API de Carros",
                contact = @Contact(
                        name = "Thierry Henry Moreira Pimenta Silva"
                )
        )
)
public class SwaggerConfig {
}