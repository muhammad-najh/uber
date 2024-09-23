package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.WalletTransactionDto;
import com.skysoft.krd.uber.entities.WalletTransaction;


public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
