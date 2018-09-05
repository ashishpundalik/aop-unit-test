package com.aoptest.app.customannotations;

import org.springframework.stereotype.Component;

@Component
public class CustomAnnotationTestDelegate {
    @MethodTimeLogger
    public void methodWithCustomAnnotation() {
//        System.out.println("asdsad");
    }
}
