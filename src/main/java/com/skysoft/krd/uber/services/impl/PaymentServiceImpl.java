package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.entities.Payment;
import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.enums.PaymentStatus;
import com.skysoft.krd.uber.repositories.PaymentRepository;
import com.skysoft.krd.uber.repositories.RideRepository;
import com.skysoft.krd.uber.services.PaymentService;
import com.skysoft.krd.uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment=paymentRepository.findByRide(ride).orElseThrow(
                () -> new RuntimeException("Payment not found for ride " + ride)
        );
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {

        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);

    }
}
