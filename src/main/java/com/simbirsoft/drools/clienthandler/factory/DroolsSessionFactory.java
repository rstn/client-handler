package com.simbirsoft.drools.clienthandler.factory;

import org.drools.runtime.StatelessKnowledgeSession;

public interface DroolsSessionFactory {
    StatelessKnowledgeSession createSession();
}
