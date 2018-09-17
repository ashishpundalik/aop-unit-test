package com.aoptest.app;

import com.aoptest.app.customannotations.MethodTimeLogger;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDelegateService {

    @MethodTimeLogger
    public void performActionWithALag() throws InterruptedException {
        Thread.sleep(500);
    }
}
