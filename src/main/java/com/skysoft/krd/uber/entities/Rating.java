package com.skysoft.krd.uber.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_rating_rider",columnList = "rider_id"),
                @Index(name = "idx_rating_driver",columnList = "driver_id")
        }
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;


    private Integer riderRating;
    private Integer driverRating;
}
