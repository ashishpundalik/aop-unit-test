package com.aoptest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AOPTesting implements CommandLineRunner {

    @Autowired
    private AnnotationDelegateService annotationDelegateService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AOPTesting.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        annotationDelegateService.performActionWithALag();
    }
}