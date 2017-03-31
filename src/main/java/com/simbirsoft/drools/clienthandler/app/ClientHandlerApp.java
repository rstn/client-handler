package com.simbirsoft.drools.clienthandler.app;

import com.simbirsoft.drools.clienthandler.service.ClientProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientHandlerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandlerApp.class);

    private ClientHandlerApp() {}

    public static void main(String[] args) {
        LOGGER.info("Инициализация");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ClientProcessingService clientProcessingMachine = (ClientProcessingService) context.getBean("DroolsClientProcessing");
        LOGGER.info("Старт обработки файлов");
        clientProcessingMachine.startProcess();
        LOGGER.info("Обработка файлов завершена");
    }
}
