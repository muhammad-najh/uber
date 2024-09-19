package com.skysoft.krd.uber.controllers;

import com.skysoft.krd.uber.dto.*;
import com.skysoft.krd.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/riders")
@RequiredArgsConstructor //lambok to create constractor
@Secured("ROLE_RIDER")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver/{riderId}/{rating}")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long riderId, @PathVariable Integer rating) {
        return ResponseEntity.ok(riderService.rateDriver(riderId,rating));
    }
    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile() {
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0")Integer pageOffset,
                                                       @RequestParam(defaultValue = "10",required = false)Integer pageSize) {
        PageRequest pageRequest=PageRequest.of(pageOffset,pageSize);
        return ResponseEntity.ok(riderService.getMyAllMyRides(pageRequest));

    }
}
