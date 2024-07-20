package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
