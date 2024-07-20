package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //all bussines logic should be a service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
