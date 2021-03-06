package com.simbirsoft.drools.clienthandler.service.file;


import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.ClientResult;

public interface ClientStorage {

    boolean hasNextClient();

    Client loadNextClient() throws LoadClientException;

    void storeClientResult(ClientResult clientResult) throws StoreClientException;

    void markProcessed(Client client) throws StoreClientException;
}
