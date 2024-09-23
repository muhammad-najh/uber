package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.RiderDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Rating;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.exceptions.ResourceNotFoundException;
import com.skysoft.krd.uber.exceptions.RuntimeConflictException;
import com.skysoft.krd.uber.repositories.DriverRepository;
import com.skysoft.krd.uber.repositories.RatingRepository;
import com.skysoft.krd.uber.repositories.RiderRepository;
import com.skysoft.krd.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver=ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).
                orElseThrow(()->new ResourceNotFoundException("Ride not found with rid id "+ride.getId()));


        if(ratingObj.getDriverRating()!=null)
            throw new RuntimeConflictException("Driver Already Rated,Can not rate again");

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average()
                .orElse(0.0);

        driver.setRating(newRating);

        Driver savedDriver=driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);


    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider=ride.getRider();

        Rating ratingObj=ratingRepository.findByRide(ride).orElseThrow(
                ()->new ResourceNotFoundException("Ride not found with rid id "+ride.getId())
        );

        if(ratingObj.getRiderRating()!=null)
            throw new RuntimeConflictException("Rider Already Rated,Can not rate again");

        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating =ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average()
                .orElse(0.0);

        rider.setRating(newRating);
        Rider savedRider=riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating =Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
