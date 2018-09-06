package com.aoptest.app.customannotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodTimeLoggerAspect.class);

    @Around("@annotation(com.aoptest.app.customannotations.MethodTimeLogger)")
    public Object methodTimeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;

        LOGGER.info("The method " + joinPoint.getSignature() + " took " + timeTaken + "ms");
        return proceed;
    }
}
