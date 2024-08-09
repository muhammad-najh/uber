package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.repositories.DriverRepository;
import com.skysoft.krd.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

 //all bussines logic should be a service
@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
     private final DriverRepository driverRepository;

     @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {

        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
