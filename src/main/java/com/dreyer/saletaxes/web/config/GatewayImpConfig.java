package com.dreyer.saletaxes.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.dreyer.saletaxes.jpa.gatewayimp")
public class GatewayImpConfig {
}
