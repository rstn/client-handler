package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;

import java.util.List;

public interface ClientsService {
    void process(List<DroolsClient> clients);
}
