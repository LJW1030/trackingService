package com.tracking.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(createInfo())
            .schemaRequirement(HttpHeaders.AUTHORIZATION, createSecurityScheme());
    }

    private Info createInfo() {
        return new Info().title("TRACKING API")
            .version("1.0.0")
            .description("TRACKING API 입니다.");
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION)
            .description("Authorization: Bearer ~");
    }

}
