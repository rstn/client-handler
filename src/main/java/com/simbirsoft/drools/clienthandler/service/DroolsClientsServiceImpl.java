package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.factory.ProcessingFactory;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;
import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DroolsClientsServiceImpl")
public class DroolsClientsServiceImpl implements DroolsClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsClientsServiceImpl.class);

    @Autowired
    @Qualifier("ClientsProcessingFactory")
    ProcessingFactory<StatelessKnowledgeSession> processingFactory;

    @Override
    public void process(List<DroolsClient> clients) {
        LOGGER.debug("Running product logic");
        StatelessKnowledgeSession statelessKnowledgeSession = processingFactory.createProcessingObject();
        LOGGER.debug("Running rules for products...");
        statelessKnowledgeSession.execute(clients);
        LOGGER.debug("...finished running products.");
    }
}
