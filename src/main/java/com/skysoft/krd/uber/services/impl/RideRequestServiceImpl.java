package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.entities.RideRequest;
import com.skysoft.krd.uber.exceptions.ResourceNotFoundException;
import com.skysoft.krd.uber.exceptions.RideRequestCanNotBeFoundException;
import com.skysoft.krd.uber.repositories.RideRequestRepository;
import com.skysoft.krd.uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl  implements RideRequestService {

    private final RideRequestRepository repository;
    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return repository.findById(rideRequestId).orElseThrow(()->{
            throw new ResourceNotFoundException("Ride Request Not Found");
        });
    }

    @Override
    public void updateRideRequest(RideRequest rideRequest) {
        // i dont want to create new ride request i just want to update it
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()
                -> new RideRequestCanNotBeFoundException("Ride Request Can not be found"));

        rideRequestRepository.save(rideRequest);


    }
}
