package com.skysoft.krd.uber.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }


    public ResourceNotFoundException(String message) {
        super(message);
    }
}
