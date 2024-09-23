package com.skysoft.krd.uber.exceptions;

public class RideRequestCanNotBeAcceptedException extends RuntimeException {
    public RideRequestCanNotBeAcceptedException() {
    }
    public RideRequestCanNotBeAcceptedException(String message) {
        super(message);
    }
}
