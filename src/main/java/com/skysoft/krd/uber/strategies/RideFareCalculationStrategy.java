package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER =10;
    double SURGE_FACTOR = 2;  //this will depend on alot of things in realword but we will pu static value here
    // we can depend on weather
    double calculateFare(RideRequest rideRequest);
}
