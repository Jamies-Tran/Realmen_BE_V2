package com.capstone.realmen.controller.handler.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException() {
        super();
    }
}
