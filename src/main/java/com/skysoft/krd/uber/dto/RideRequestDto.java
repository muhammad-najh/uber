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

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;
    private LocalDateTime requestedTime;

    private RiderDto rider;
    private RideRequestStatus rideRequestStatus;


}
