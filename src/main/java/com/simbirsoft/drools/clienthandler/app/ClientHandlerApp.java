package com.simbirsoft.drools.clienthandler.app;

import com.simbirsoft.drools.clienthandler.service.ClientProcessingMachine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientHandlerApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ClientProcessingMachine clientProcessingMachine =(ClientProcessingMachine)context.getBean("DroolsClientProcessingMachine");
        clientProcessingMachine.startProcess();
    }
}
