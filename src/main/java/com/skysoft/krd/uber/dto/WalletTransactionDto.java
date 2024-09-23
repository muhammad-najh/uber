package com.skysoft.krd.uber.dto;

import com.skysoft.krd.uber.entities.Ride;
import com.skysoft.krd.uber.entities.Wallet;
import com.skysoft.krd.uber.entities.enums.TransactionMethod;
import com.skysoft.krd.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private LocalDateTime timeStamp;
    private WalletDto wallet;
}
