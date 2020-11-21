package com.icemelon.scheduler;

import com.icemelon.scheduler.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@SpringBootApplication
@EnableWebMvc
public class SchedulerApplication {


    /*
     * TODO - add logging(logback)
     *      - implement view controller
     */
    public static void main(String[] args) {

        SpringApplication.run(SchedulerApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);

        registrationBean.addUrlPatterns("/schedule/*");

        return registrationBean;
    }

}
