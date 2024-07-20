package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.dto.RiderDto;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto ride);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rate);
    RiderDto getMyProfile(); //no need to have id because it will come across spring security concepts
    List<RideDto> getMyAllMyRides();
}
