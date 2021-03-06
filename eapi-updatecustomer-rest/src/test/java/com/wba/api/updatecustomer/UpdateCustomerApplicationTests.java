package com.wba.api.updatecustomer;

import com.wba.api.updatecustomer.controller.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {
        CustomerController.class,
        com.wba.api.updatecustomer.service.CustomerTopicServiceImpl.class
}, loader = AnnotationConfigContextLoader.class, initializers = ConfigFileApplicationContextInitializer.class)

public class UpdateCustomerApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(UpdateCustomerApplicationTests.class);

    @Test
    public void loadContext() {

    }
}
