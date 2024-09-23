package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Payment;
import com.skysoft.krd.uber.entities.Wallet;
import com.skysoft.krd.uber.entities.enums.PaymentStatus;
import com.skysoft.krd.uber.entities.enums.TransactionMethod;
import com.skysoft.krd.uber.repositories.PaymentRepository;
import com.skysoft.krd.uber.services.PaymentService;
import com.skysoft.krd.uber.services.WalletService;
import com.skysoft.krd.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//cash on delevary

//rider -> 100
//driver ->70
//30 comession
@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();

        double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;

        Wallet wallet=walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,
                null,payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRM);
        paymentRepository.save(payment);

    }
}
