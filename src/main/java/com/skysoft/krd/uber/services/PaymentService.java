package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.entities.Payment;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.enums.PaymentStatus;
import org.springframework.stereotype.Service;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
