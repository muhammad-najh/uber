package com.skysoft.krd.uber.dto;

import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.enums.PaymentMethod;
import com.skysoft.krd.uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;

    private Point pickupLocation;

    private Point dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;


}
