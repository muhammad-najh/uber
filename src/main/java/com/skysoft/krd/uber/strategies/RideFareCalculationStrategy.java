package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
