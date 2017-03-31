package com.simbirsoft.drools.clienthandler.file;

public class StoreClientException extends Exception {

    public StoreClientException() {
    }

    public StoreClientException(String message) {
        super(message);
    }

    public StoreClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
