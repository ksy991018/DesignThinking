package com.icemelon.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.swing.*;

@Configuration
public class ThyemeLeafConfig {

    @Value("${spring.thymeleaf.cache}")
    private boolean isCache;

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {

        return new InternalResourceViewResolver();
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(isCache);
        return templateResolver;
    }

    @Bean
    @Autowired
    public SpringTemplateEngine templateEngine(MessageSource messageSource, SpringResourceTemplateResolver resolver) {

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        templateEngine.setTemplateEngineMessageSource(messageSource);

        return templateEngine;
    }

    @Bean
    @Autowired
    public ViewResolver viewResolver(MessageSource messageSource, SpringTemplateEngine engine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(0);
        return viewResolver;
    }
}
