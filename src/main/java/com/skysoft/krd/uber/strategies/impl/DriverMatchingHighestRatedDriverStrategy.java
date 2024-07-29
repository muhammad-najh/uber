package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequestD) {
        return List.of();
    }
}
