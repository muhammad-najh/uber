package com.skysoft.krd.uber.strategies;

import com.skysoft.krd.uber.entities.enums.PaymentMethod;
import com.skysoft.krd.uber.strategies.impl.CashPaymentStrategy;
import com.skysoft.krd.uber.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {

        return switch (paymentMethod) {
            case WALLET-> walletPaymentStrategy;
            case CASH-> cashPaymentStrategy;
        };
    }
}
