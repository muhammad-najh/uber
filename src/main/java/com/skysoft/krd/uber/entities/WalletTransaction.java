package com.skysoft.krd.uber.entities;

import com.skysoft.krd.uber.entities.enums.TransactionMethod;
import com.skysoft.krd.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(
        indexes = {
                @Index(name = "idx_wallet_transaction_wallet",columnList = "wallet_id"),
                @Index(name = "idx_wallet_transaction_ride",columnList = "ride_id")
        }
)
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    @ManyToOne
    private Ride ride;
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @ManyToOne
    private Wallet wallet;
}
