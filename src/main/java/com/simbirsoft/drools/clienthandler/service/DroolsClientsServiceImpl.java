package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.factory.DroolsSessionFactory;
import com.simbirsoft.drools.clienthandler.model.ClientResult;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("DroolsClientsServiceImpl")
public class DroolsClientsServiceImpl implements DroolsClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsClientsServiceImpl.class);

    @Autowired
    @Qualifier("DroolsClientsSessionFactory")
    DroolsSessionFactory<KieSession> droolsSessionFactory;

    @Override
    public List<ClientResult> process(List<DroolsClient> clients) {
        LOGGER.debug("Running drools clients session");
        KieSession kieSession = droolsSessionFactory.createSession();
        LOGGER.debug("Running rules for clients...");
        clients.forEach(kieSession::insert);
        kieSession.fireAllRules();
        kieSession.dispose();
        LOGGER.debug("...finished running clients.");
        return clients.stream().map(DroolsClient::getClientResult).collect(Collectors.toList());
    }
}
