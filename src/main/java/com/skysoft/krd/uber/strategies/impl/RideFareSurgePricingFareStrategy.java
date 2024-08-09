package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.services.DistanceService;
import com.skysoft.krd.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingFareStrategy implements RideFareCalculationStrategy {
    private  final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance*SURGE_FACTOR*RIDE_FARE_MULTIPLIER;
    }
    }

