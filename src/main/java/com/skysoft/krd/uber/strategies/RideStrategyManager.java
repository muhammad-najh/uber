package com.skysoft.krd.uber.strategies;


import com.skysoft.krd.uber.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.skysoft.krd.uber.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.skysoft.krd.uber.strategies.impl.RideFareDefualtFareCalculationStrategy;
import com.skysoft.krd.uber.strategies.impl.RideFareSurgePricingFareStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestStrategy;
    private final RideFareDefualtFareCalculationStrategy rideFareDefualtFareCalculationStrategy;
    private final RideFareSurgePricingFareStrategy rideFareSurgePricingFareStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double rideRate){
        if (rideRate>4.8){
            return driverMatchingHighestRatedStrategy;
        }else {
            return driverMatchingNearestStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        // surge Time just to example will be between 6PM till 9PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime =LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if (isSurgeTime){
            return rideFareSurgePricingFareStrategy;
        }else {
            return rideFareDefualtFareCalculationStrategy;
        }
    }
}
