package com.simbirsoft.drools.clienthandler.factory;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

@Component("DroolsClientsSessionFactory")
public class DroolsClientsSessionFactory implements DroolsSessionFactory<KieSession> {

    public static final String CLIENTS_KSESSION = "ksession-clients";

    private final KieContainer kieContainer;

    protected DroolsClientsSessionFactory() {
        kieContainer = KieServices.Factory.get().getKieClasspathContainer();
    }

    @Override
    public KieSession createSession() {
        return kieContainer.newKieSession(CLIENTS_KSESSION);
    }
}
