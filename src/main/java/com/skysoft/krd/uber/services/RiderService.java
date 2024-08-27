package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto ride);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rate); // homework *******************************
    RiderDto getMyProfile(); //no need to have id because it will come across spring security concepts
    Page<RideDto> getMyAllMyRides(PageRequest pageRequest);
    Rider createNewRider(User user);
    Rider getCurrentRider();

}
