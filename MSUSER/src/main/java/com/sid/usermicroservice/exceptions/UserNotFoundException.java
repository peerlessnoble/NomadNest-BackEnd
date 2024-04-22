package com.sid.usermicroservice.exceptions;

public class    UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
