package com.aoptest.app.customannotations;


import com.aoptest.app.AOPTesting;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
@PrepareForTest({MethodTimeLoggerAspect.class, LoggerFactory.class})
@ContextConfiguration(classes = AOPTesting.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class AspectTestStrategyIntegration {

    @Autowired
    private CustomAnnotationTestDelegate customAnnotationTestDelegate;

    private static Logger loggerMock;

    @BeforeClass
    public static void classSetup() {
        loggerMock = mock(Logger.class);
        mockStatic(System.class);
        mockStatic(LoggerFactory.class);

        when(LoggerFactory.getLogger(MethodTimeLoggerAspect.class)).thenReturn(loggerMock);
    }

    @Test
    public void shouldLogMethodTiming() {
        long startTime = 100L;
        long endTime = 400L;
        long expectedTimeTaken = endTime - startTime;

        when(System.currentTimeMillis()).thenReturn(startTime, endTime);

        customAnnotationTestDelegate.methodWithCustomAnnotation();

        Method method = ReflectionUtils.findMethod(CustomAnnotationTestDelegate.class,
                "methodWithCustomAnnotation");

        String methodSignature = new StringBuilder()
                .append(method.getReturnType().toString())
                .append(" ")
                .append(CustomAnnotationTestDelegate.class.getCanonicalName())
                .append(".")
                .append(method.getName())
                .append("()")
                .toString();

        Mockito.verify(loggerMock).info("The method "+methodSignature+" took "+expectedTimeTaken+"ms");
    }
}
