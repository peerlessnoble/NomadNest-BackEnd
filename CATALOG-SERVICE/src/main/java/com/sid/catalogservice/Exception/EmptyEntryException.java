package com.sid.catalogservice.Exception;

public class EmptyEntryException extends RuntimeException {
    public EmptyEntryException(String message) {
        super(message);
    }
}
