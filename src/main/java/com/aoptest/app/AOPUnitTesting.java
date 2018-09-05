package com.aoptest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("!test")
public class AOPUnitTesting implements CommandLineRunner {

    @Autowired
    private FooService fooService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AOPUnitTesting.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        fooService.performActionWithALag();
    }
}