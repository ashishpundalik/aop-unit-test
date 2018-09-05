package com.aoptest.app.customannotations;


import com.aoptest.app.AOPUnitTesting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
@PrepareForTest(MethodTimeLoggerAspect.class)
@ContextConfiguration(classes = AOPUnitTesting.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class AspectTestStrategyIntegration {

    @Autowired
    private CustomAnnotationTestDelegate customAnnotationTestDelegate;

    @Test
    public void shouldLogMethodTiming() {
        mockStatic(System.class);

        when(System.currentTimeMillis()).thenReturn(100L);

        customAnnotationTestDelegate.methodWithCustomAnnotation();
    }
}
