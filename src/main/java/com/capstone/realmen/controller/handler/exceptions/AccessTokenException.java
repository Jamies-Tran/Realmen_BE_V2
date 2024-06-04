package com.capstone.realmen.controller.handler.exceptions;

public class AccessTokenException extends RuntimeException {
    public AccessTokenException() {
        super();
    }

    public AccessTokenException(String msg) {
        super(msg);
    }
}
