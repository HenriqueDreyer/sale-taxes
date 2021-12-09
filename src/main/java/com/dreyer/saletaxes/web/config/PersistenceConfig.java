package com.dreyer.saletaxes.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.dreyer.saletaxes.core.domain.gateway")
@EnableJpaRepositories(basePackages = "com.dreyer.saletaxes.jpa.repository")
@EntityScan(basePackages = {"com.dreyer.saletaxes.jpa.entity", "com.dreyer.saletaxes.core.domain.gateway"})
public class PersistenceConfig {
}
