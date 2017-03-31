package com.simbirsoft.drools.clienthandler.model;


public class ClientResult {

    private long clientId;

    private long spentTotal;

    private boolean isBig;

    public long getClientId() {
        return clientId;
    }

    public ClientResult setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public long getSpentTotal() {
        return spentTotal;
    }

    public ClientResult setSpentTotal(long spentTotal) {
        this.spentTotal = spentTotal;
        return this;
    }

    public boolean isBig() {
        return isBig;
    }

    public ClientResult setBig(boolean big) {
        isBig = big;
        return this;
    }
}
