package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto);
}
