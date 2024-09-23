package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.WalletTransactionDto;
import com.skysoft.krd.uber.entities.WalletTransaction;
import com.skysoft.krd.uber.repositories.WalletTransactionRepository;
import com.skysoft.krd.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

   private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);


    }
}
