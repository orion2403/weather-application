package com.orion.exception;

public class IncorrectPasswordEnteredException extends RuntimeException {
    public IncorrectPasswordEnteredException(String message) {
        super(message);
    }
}
