package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.factory.DroolsSessionFactory;
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
    @Qualifier("DroolsClientsSessionFactory")
    DroolsSessionFactory droolsSessionFactory;

    @Override
    public void process(List<DroolsClient> clients) {
        LOGGER.debug("Running drools clients session");
        StatelessKnowledgeSession statelessKnowledgeSession = droolsSessionFactory.createSession();
        LOGGER.debug("Running rules for clients...");
        statelessKnowledgeSession.execute(clients);
        LOGGER.debug("...finished running clients.");
    }
}
