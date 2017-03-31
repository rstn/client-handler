package com.simbirsoft.drools.clienthandler.model;


public class Subscriber {

    private long id;

    private long spent;

    public long getId() {
        return id;
    }

    public Subscriber setId(long id) {
        this.id = id;
        return this;
    }

    public long getSpent() {
        return spent;
    }

    public Subscriber setSpent(long spent) {
        this.spent = spent;
        return this;
    }
}
