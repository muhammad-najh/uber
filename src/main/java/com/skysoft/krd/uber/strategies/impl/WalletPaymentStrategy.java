package com.skysoft.krd.uber.strategies.impl;

import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.Payment;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.Wallet;
import com.skysoft.krd.uber.entities.enums.PaymentStatus;
import com.skysoft.krd.uber.entities.enums.TransactionMethod;
import com.skysoft.krd.uber.repositories.PaymentRepository;
import com.skysoft.krd.uber.services.PaymentService;
import com.skysoft.krd.uber.services.WalletService;
import com.skysoft.krd.uber.strategies.PaymentStrategy;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Rider has 232 , Driver had 500
//Ride cost 100 , commission 30
//Rider -232-100 = 132
//driver (100-30) + 500 = 570
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver=payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null,payment.getRide(), TransactionMethod.RIDE);

        double driversCut=payment.getAmount()*(1-PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driversCut,null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRM);
        paymentRepository.save(payment);

    }
}
