package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import com.skysoft.krd.uber.exceptions.DriverNotAvailableException;
import com.skysoft.krd.uber.exceptions.RideRequestCanNotBeAcceptedException;
import com.skysoft.krd.uber.repositories.DriverRepository;
import com.skysoft.krd.uber.services.DriverService;
import com.skysoft.krd.uber.services.RideRequestService;
import com.skysoft.krd.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private  final RideRequestService requestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    public RideDto acceptRide(Long rideRequestID) {

        RideRequest rideRequest=requestService.findRideRequestById(rideRequestID);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RideRequestCanNotBeAcceptedException("Ride Request can not be accepted because status is"
                    +rideRequest.getRideRequestStatus());
        }
        Driver currentDriver=getCurrentDriver();
        if (!currentDriver.isAvailable()){
            throw new DriverNotAvailableException("Driver is not available");
        }

        currentDriver.setAvailable(false);
        Driver savedDriver=driverRepository.save(currentDriver);
        Ride ride=rideService.createNewRide(rideRequest,savedDriver);



        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId,String otp) {
        Ride ride=rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver can not start a ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not Confirmed and can not be started status is "
                    +ride.getRideStatus());

        }

        if (!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid OTP "+ otp);
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide= rideService.updateRideStatus(ride,RideStatus.ONGOING);

        return modelMapper.map(savedRide, RideDto.class);
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

    @Override
    public Driver getCurrentDriver() {

        //TODO after immplementing spring secuirty
        // we will get current driver from spring secuirty context but for now i will use dummy
        return driverRepository.findById(3l).orElseThrow(() -> {
            throw new RuntimeException("Driver not found");});
    }
}
