package com.skysoft.krd.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

    private Long id;
    private UserDto user;
    private  Double balance;
    List<WalletTransactionDto> transactions;
}
