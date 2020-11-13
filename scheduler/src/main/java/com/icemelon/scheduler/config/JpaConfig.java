package com.icemelon.scheduler.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.icemelon.scheduler.repository" , entityManagerFactoryRef = "entityManagerFactory" , transactionManagerRef = "transactionManager")
public class JpaConfig {
}
