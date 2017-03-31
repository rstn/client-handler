package com.simbirsoft.drools.clienthandler.model;


public class DroolsClient {

    private final Client client;

    private final ClientResult clientResult;

    public DroolsClient(Client client, ClientResult clientResult) {
        this.client = client;
        this.clientResult = clientResult;
    }

    public Client getClient() {
        return client;
    }

    public ClientResult getClientResult() {
        return clientResult;
    }
}
