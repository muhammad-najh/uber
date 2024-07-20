package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import com.skysoft.krd.uber.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl  implements RideService {
    @Override
    public Ride getRideById(Long id) {
        return null;
    }

    @Override
    public void matchWithDriver(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}
