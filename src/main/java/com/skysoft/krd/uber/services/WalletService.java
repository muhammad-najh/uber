package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.entities.Wallet;
import com.skysoft.krd.uber.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId,
                            Ride ride, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId,
                                 Ride ride, TransactionMethod transactionMethod);
    void withdrawAllMoneyFromWallet();
    Wallet findWalletById(Long walletID);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);


}
