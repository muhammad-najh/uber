package com.skysoft.krd.uber.exceptions;

public class DriverNotAvailableException extends RuntimeException {
    public DriverNotAvailableException() {}
    public DriverNotAvailableException(String message) {
        super(message);
    }
}
