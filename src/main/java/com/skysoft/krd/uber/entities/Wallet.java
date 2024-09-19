package com.skysoft.krd.uber.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.DETACH)
    private User user;
    private  Double balance=0.0;
    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
    List<WalletTransaction> transactions;
}
