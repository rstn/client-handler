package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.model.DroolsClient;

import java.util.List;

public interface DroolsClientsService {
    void process(List<DroolsClient> clients);
}
