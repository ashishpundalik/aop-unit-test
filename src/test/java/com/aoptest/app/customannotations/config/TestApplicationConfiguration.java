package com.aoptest.app.customannotations.config;

import com.aoptest.app.customannotations.MethodTimeLoggerAspect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
@EnableAutoConfiguration
public class TestApplicationConfiguration {

    @Bean
    public MethodTimeLoggerAspect methodTimeLoggerAspect() {
        return new MethodTimeLoggerAspect();
    }
}