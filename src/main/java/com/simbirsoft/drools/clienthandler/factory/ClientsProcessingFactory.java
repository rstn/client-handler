package com.simbirsoft.drools.clienthandler.factory;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("ClientsProcessingFactory")
public class ClientsProcessingFactory implements ProcessingFactory<StatelessKnowledgeSession> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public StatelessKnowledgeSession createProcessingObject() {
        return (StatelessKnowledgeSession)applicationContext.getBean("clientsKSession");
    }
}
