package com.capstone.realmen.controller.handler.exceptions;

public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }
}
