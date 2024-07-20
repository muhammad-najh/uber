package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.services.DriverService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RideDto acceptRide(RideDto ride) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rate) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getMyAllMyRides() {
        return List.of();
    }
}
