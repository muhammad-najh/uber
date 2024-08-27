package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.RideRequestDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import com.skysoft.krd.uber.exceptions.RideNotFoundException;
import com.skysoft.krd.uber.repositories.RideRepository;
import com.skysoft.krd.uber.repositories.RideRequestRepository;
import com.skysoft.krd.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
//@Transactional
public class RideServiceImpl  implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestRepository rideRequestRepository;
    private final RideRequestServiceImpl rideRequestServiceImpl;
    private final ModelMapper modelMapper;
    @Override
    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElseThrow(()-> new RideNotFoundException("Ride Not Found Exception"));
    }



    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride=modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);

        rideRequestServiceImpl.updateRideRequest(rideRequest);


        return rideRepository.save(ride); //it will return T
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfDRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }

    @Override
    public Double numberRidesOfRider(Rider rider) {
        return 0.0;
    }

    @Override
    public Double numberRidesOfDriver(Driver driver) {
        return 0.0;
    }


    private String generateRandomOtp(){
        Random rand = new Random();
        int otpInt=rand.nextInt(10000);
        return String.format("%04d",otpInt);
    }
}
