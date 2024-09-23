package com.skysoft.krd.uber.exceptions;

public class RideRequestCanNotBeFoundException  extends RuntimeException{

    public RideRequestCanNotBeFoundException(){}
    public RideRequestCanNotBeFoundException(String message){
        super(message);
    }
}
