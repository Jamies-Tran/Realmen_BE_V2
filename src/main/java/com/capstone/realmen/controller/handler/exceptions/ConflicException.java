package com.capstone.realmen.controller.handler.exceptions;

public class ConflicException extends RuntimeException {
    
    public ConflicException() {
        super();
    }

    public ConflicException(String msg) {
        super(msg);
    }
}
