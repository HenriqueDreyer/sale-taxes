package com.dreyer.saletaxes.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerUiLayout() {
        return new OpenAPI()
                .info(this.apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Sale-Taxes API")
                .version("0.0.1")
                .contact(this.apiContact())
                .description("API Sale-Taxes (Liferay Exercise)")
                .termsOfService("http://swagger.io/terms/");
    }

    private Contact apiContact() {
        return new Contact()
                .name("Henrique Dreyer")
                .email("henriquedreyer@gmail.com")
                .url("https://github.com/HenriqueDreyer/");
    }
}
