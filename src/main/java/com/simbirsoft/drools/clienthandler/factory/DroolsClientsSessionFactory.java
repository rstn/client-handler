package com.simbirsoft.drools.clienthandler.factory;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("DroolsClientsSessionFactory")
public class DroolsClientsSessionFactory implements DroolsSessionFactory {

    public static final String CLIENTS_KSESSION = "clientsKSession";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public StatelessKnowledgeSession createSession() {
        return (StatelessKnowledgeSession)applicationContext.getBean(CLIENTS_KSESSION);
    }
}
