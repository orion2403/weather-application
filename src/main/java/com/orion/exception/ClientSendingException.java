package com.orion.exception;

public class ClientSendingException extends RuntimeException {
    public ClientSendingException(String message) {
        super(message);
    }
}
