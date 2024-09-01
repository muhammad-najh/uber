package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.*;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import com.skysoft.krd.uber.repositories.DriverRepository;
import com.skysoft.krd.uber.repositories.RideRequestRepository;
import com.skysoft.krd.uber.repositories.RiderRepository;
import com.skysoft.krd.uber.services.DriverService;
import com.skysoft.krd.uber.services.RatingService;
import com.skysoft.krd.uber.services.RideService;
import com.skysoft.krd.uber.services.RiderService;
import com.skysoft.krd.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideStrategyManager rideStrategyManager;
    private final RideService rideService;
    private final DriverService driverService;
    private final DriverRepository driverRepository;
    private final RatingService ratingService;


    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider=getCurrentRider(); //get current rider
        RideRequest rideRequest= modelMapper.map(rideRequestDto, RideRequest.class);
        double fare= rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setRider(rider);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setFare(fare);
        rideRequestRepository.save(rideRequest);
        List<Driver> drivers=rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
        // TODO send notifications to all drivers
        return modelMapper.map(rideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider=getCurrentRider();
        Ride ride=rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not belong to this ride "+rideId);

        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
                throw new RuntimeException("Ride can not be cancelled, because ride status is "+ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rate) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider=getCurrentRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not owner of ride "+rideId);
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED hence can not start rating " +
                    ", status: "+ride.getRideStatus());
        }
        return ratingService.rateDriver(ride,rate);
    }

    @Override
    public RiderDto getMyProfile() {

        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getMyAllMyRides(PageRequest pageRequest) {

        Rider currentRider=getCurrentRider();
        return rideService.getAllRidesOfDRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        //TODO implement Spring Secuirty Concepts to get Current Rider Information

        return riderRepository.findById(1L).orElseThrow(() -> {
            throw new NoSuchElementException("No Rider found");
        }); //DEMO
    }
}
