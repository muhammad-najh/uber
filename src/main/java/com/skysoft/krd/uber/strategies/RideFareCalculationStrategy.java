package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER =10;
    double calculateFare(RideRequest rideRequest);
}
