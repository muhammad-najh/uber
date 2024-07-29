package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.services.DistanceService;
import com.skysoft.krd.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Primary
public class RideFareDefualtFareCalculationStrategy implements RideFareCalculationStrategy {
    private  final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        //ToDO call the third party api to fetch the distance OSRM

        distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
