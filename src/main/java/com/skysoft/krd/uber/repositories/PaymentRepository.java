package com.skysoft.krd.uber.repositories;

import com.skysoft.krd.uber.entities.Payment;
import com.skysoft.krd.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
