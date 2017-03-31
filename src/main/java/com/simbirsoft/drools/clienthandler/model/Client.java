package com.simbirsoft.drools.clienthandler.model;


import java.util.List;

public class Client {

    private long clientId;

    private List<Subscriber> subscribers;

    public long getClientId() {
        return clientId;
    }

    public Client setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Client setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
        return this;
    }
}
