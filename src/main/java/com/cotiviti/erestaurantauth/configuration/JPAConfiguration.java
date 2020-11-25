package com.cotiviti.erestaurantauth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.cotiviti.erestaurantauth")
@EntityScan(basePackages = "com.cotiviti.erestaurantauth.model")
@EnableJpaRepositories(basePackages = "com.cotiviti.erestaurantauth.repository")
public class JPAConfiguration {
}
