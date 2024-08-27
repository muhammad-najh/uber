package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    /*all services can call this core service , anything related to ride will be defined here no need to repeat
    our code everywhere.*/

    Ride getRideById(Long id);
//    void matchWithDriver(RideRequestDto rideRequestDto); already done in rider
    Ride createNewRide(RideRequest rideRequest, Driver driver);
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
    Page<Ride> getAllRidesOfDRider(Rider rider, PageRequest pageRequest);
    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
    Double  numberRidesOfRider(Rider rider);
    Double  numberRidesOfDriver(Driver driver);
}
