package com.example.Saga_Wallet.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class TransferRequestDTO {
    private Long fromWalletId; // fromUserId
    private Long toWalletId; // toUserId
    private BigDecimal amount;
    private String description;
}
