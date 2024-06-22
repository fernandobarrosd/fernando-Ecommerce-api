package com.fernando.fernando_ecommerce_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    private SecurityScheme createJWTScheme() {
        return new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
    }

    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
        .addSecurityItem(
            new SecurityRequirement()
            .addList("JWT")
        )
        .components(
            new Components()
            .addSecuritySchemes("JWT", createJWTScheme())
        )
        .info(
            new Info()
            .title("Fernando Ecommerce API")
            .description("Uma API de ecommerce")
            .version("1.0")
            .contact(
                new Contact()
                .email("fdebarros0910-2004@hotmail.com")
                .name("Fernando de Barros")
                .url("https://github.com/fernandobarrosd")
            )
        );
    }
}