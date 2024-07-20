package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.strategies.RideFareCalculationStrategy;

public class RideFareDefualtFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
