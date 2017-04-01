package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.model.ClientResult;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;

import java.util.List;

public interface DroolsClientsService {
    List<ClientResult> process(List<DroolsClient> clients);
}
