package com.skysoft.krd.uber.dto;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.enums.PaymentMethod;
import com.skysoft.krd.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
public class RideDto {

    private Long id;

    private PointDto pickupLocation; //envelope will occur if its not PointDto

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String otp;
}
