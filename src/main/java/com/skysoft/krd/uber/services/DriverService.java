package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RiderDto;

import java.util.List;

public interface DriverService {
    RideDto acceptRide(RideDto ride);
    RideDto cancelRide(Long rideId);
    RideDto startRide(Long rideId);
    RideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId,Integer rate);
    DriverDto getMyProfile(); //no need to have id because it will come across spring security concepts
    List<RideDto> getMyAllMyRides();


}
