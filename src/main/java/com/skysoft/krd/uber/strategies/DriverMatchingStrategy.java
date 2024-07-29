package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
