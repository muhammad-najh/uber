package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void updateRideRequest(RideRequest rideRequest);
}
