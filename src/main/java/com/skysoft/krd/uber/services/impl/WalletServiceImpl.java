package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.RideDto;
import com.skysoft.krd.uber.dto.WalletDto;
import com.skysoft.krd.uber.dto.WalletTransactionDto;
import com.skysoft.krd.uber.entities.*;
import com.skysoft.krd.uber.entities.enums.TransactionMethod;
import com.skysoft.krd.uber.entities.enums.TransactionType;
import com.skysoft.krd.uber.exceptions.ResourceNotFoundException;
import com.skysoft.krd.uber.repositories.WalletRepository;
import com.skysoft.krd.uber.services.WalletService;
import com.skysoft.krd.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl  implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId,
                                   Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet=findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction=
                WalletTransaction.builder().transactionId(transactionId)
                        .ride(ride)
                        .wallet(wallet)
                        .transactionType(TransactionType.CREDIT)
                        .transactionMethod(transactionMethod)
                        .amount(amount)
                        .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
       return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId,
                                        Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet=findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);


        WalletTransaction walletTransaction=
                WalletTransaction.builder().transactionId(transactionId)
                        .ride(ride)
                        .wallet(wallet)
                        .transactionType(TransactionType.DEBIT)
                        .transactionMethod(transactionMethod)
                        .amount(amount)
                        .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);


        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletID) {
        return walletRepository.findById(walletID).orElseThrow(
                () -> new ResourceNotFoundException("Wallet not found")
        );
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = Wallet.builder().user(user).build();
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("wallet not found with id "+user.getId()));

    }
}
