package com.aoptest.app.customannotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeLoggerAspect {

    private static final Logger LOGGER = LogManager.getLogger();

    @Around("@annotation(com.aoptest.app.customannotations.MethodTimeLogger)")
    public Object methodTimeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;

        LOGGER.info("The method " + joinPoint.getSignature() + "took " + timeTaken + "ms");
        return proceed;
    }
}
