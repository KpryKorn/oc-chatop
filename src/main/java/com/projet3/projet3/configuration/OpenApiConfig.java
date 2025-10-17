package com.projet3.projet3.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI OpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("API Documentation")
                                                .version("v1.0.0"))
                                .servers(List.of(
                                                new Server()
                                                                .url("http://localhost:8080/api")
                                                                .description("Serveur de développement")))
                                .addSecurityItem(new SecurityRequirement().addList("jwt"))
                                .components(new Components()
                                                .addSecuritySchemes("jwt",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")
                                                                                .description("Authentification JWT (Bearer Token)")));
        }
}
