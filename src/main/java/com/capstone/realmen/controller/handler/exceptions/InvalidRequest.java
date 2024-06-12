package com.capstone.realmen.controller.handler.exceptions;

public class InvalidRequest extends RuntimeException {

    public InvalidRequest() {
        super();
    }

    public InvalidRequest(String msg) {
        super(msg);
    }
}
