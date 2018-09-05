package com.aoptest.app;

import com.aoptest.app.customannotations.MethodTimeLogger;
import org.springframework.stereotype.Component;

@Component
public class FooService {

    @MethodTimeLogger
    public void performActionWithALag() throws InterruptedException {
        Thread.sleep(500);
    }
}
