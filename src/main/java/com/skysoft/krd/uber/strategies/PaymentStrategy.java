package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment)    ;

}
