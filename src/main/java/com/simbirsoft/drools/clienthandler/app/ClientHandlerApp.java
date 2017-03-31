package com.simbirsoft.drools.clienthandler.app;

import com.simbirsoft.drools.clienthandler.service.ClientProcessingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientHandlerApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ClientProcessingService clientProcessingMachine =(ClientProcessingService)context.getBean("DroolsClientProcessingMachine");
        clientProcessingMachine.startProcess();
    }
}
