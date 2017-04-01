package com.simbirsoft.drools.clienthandler.factory;

public interface DroolsSessionFactory<T> {
    T createSession();
}
