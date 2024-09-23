package com.skysoft.krd.uber.repositories;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Rating;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDriver(Driver driver);
    List<Rating> findByRider(Rider rider);

    Optional<Rating> findByRide(Ride ride);
}
