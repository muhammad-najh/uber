package com.skysoft.krd.uber.exceptions;

public class RideNotFoundException extends RuntimeException {
    public RideNotFoundException() {

    }
    public RideNotFoundException(String message) {
        super(message);
    }
}
