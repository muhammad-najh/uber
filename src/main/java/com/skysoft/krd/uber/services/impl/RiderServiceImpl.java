package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import com.skysoft.krd.uber.repositories.RideRepository;
import com.skysoft.krd.uber.repositories.RideRequestRepository;
import com.skysoft.krd.uber.repositories.RiderRepository;
import com.skysoft.krd.uber.services.RiderService;
import com.skysoft.krd.uber.strategies.DriverMatchingStrategy;
import com.skysoft.krd.uber.strategies.RideFareCalculationStrategy;
import com.skysoft.krd.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider=getCurrentRider(); //get current rider
        RideRequest rideRequest= modelMapper.map(rideRequestDto, RideRequest.class);
        double fare= rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setRider(rider);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setFare(fare);
        rideRequestRepository.save(rideRequest);
        rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

        return modelMapper.map(rideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rate) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getMyAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        //TODO implement Spring Secuirty Concepts to get Current Rider Information

        return riderRepository.findById(1l).orElseThrow(() -> {
            throw new NoSuchElementException("No Rider found");
        }); //DEMO
    }
}
