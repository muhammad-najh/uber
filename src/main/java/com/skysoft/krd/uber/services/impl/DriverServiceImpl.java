package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import com.skysoft.krd.uber.exceptions.DriverNotAvailableException;
import com.skysoft.krd.uber.exceptions.RideRequestCanNotBeAcceptedException;
import com.skysoft.krd.uber.repositories.DriverRepository;
import com.skysoft.krd.uber.repositories.RideRepository;
import com.skysoft.krd.uber.repositories.RiderRepository;
import com.skysoft.krd.uber.services.DriverService;
import com.skysoft.krd.uber.services.PaymentService;
import com.skysoft.krd.uber.services.RideRequestService;
import com.skysoft.krd.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private  final RideRequestService requestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RiderRepository riderRepository;

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

        Driver updatedDriver=updateDriverAvailability(currentDriver,false);
        Driver savedDriver=driverRepository.save(updatedDriver);
        Ride ride=rideService.createNewRide(rideRequest,savedDriver);



        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride=rideService.getRideById(rideId);

        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver()) ){
            throw new DriverNotAvailableException("some one that Didnt have permission to cancel this ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Can't be cancelled hence its not in confirm status maybe its ongoing or ended");
        }

        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);

        return modelMapper.map(savedRide, RideDto.class);
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

        paymentService.createNewPayment(savedRide);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride=rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver can not start a ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING and can not be started status is "
                    +ride.getRideStatus());

        }

        ride.setEndedAt(LocalDateTime.now());

        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.ENDED);

        updateDriverAvailability(driver,true);

        paymentService.processPayment(ride);

        return modelMapper.map(savedRide, RideDto.class);



    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rate) {
        Rider rider=rideService.getRideById(rideId).getRider();
        double numberOfRides = rideService.numberRidesOfRider(rider);
        if(numberOfRides == 0){
            numberOfRides=1;
        }
        double rating=(rider.getRating()+rate)/numberOfRides;
        rider.setRating(rating);
        Rider savedRider=riderRepository.save(rider);

        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver=getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getMyAllMyRides(PageRequest pageRequest) {
        Driver currentDriver=getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver,pageRequest).map(
               ride -> modelMapper.map(ride, RideDto.class)

        );

    }

    @Override
    public Driver getCurrentDriver() {

        //TODO after immplementing spring secuirty
        // we will get current driver from spring secuirty context but for now i will use dummy
        return driverRepository.findById(3l).orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean isAvailable) {
        driver.setAvailable(isAvailable);
        return driverRepository.save(driver);
    }
}
