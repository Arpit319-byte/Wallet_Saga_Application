package com.example.Saga_Wallet.Service.Saga.Step;


import com.example.Saga_Wallet.Entity.Wallet;
import com.example.Saga_Wallet.Repository.WalletRepository;
import com.example.Saga_Wallet.Service.Saga.SagaContext;
import com.example.Saga_Wallet.Service.Saga.SagaStep;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public class CreditDestinationWalletStep implements SagaStep {

    private WalletRepository walletRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {
        Long toWalletId=context.getLong("toWalletId");
        BigDecimal amount=context.getBigDecimal("amount");

        Wallet wallet=walletRepository.findWalletByLock(toWalletId).orElseThrow(()-> new RuntimeException("wallet not found"));
        wallet.credit(amount);
        walletRepository.save(wallet);

        context.put("toWalletBalanceAfterCredit",wallet.getBalance());
        return true;
    }

    @Override
    @Transactional
    public boolean compensate(SagaContext context) {

        Long toWalletId=context.getLong("toWalletId");
        BigDecimal amount=context.getBigDecimal("amount");

        Wallet wallet=walletRepository.findWalletByLock(toWalletId).orElseThrow(()-> new RuntimeException("wallet not found"));
        wallet.debit(amount);
        walletRepository.save(wallet);

        context.put("toWalletBalanceAfterCreditCompensation",wallet.getBalance());
        return false;
    }

    @Override
    public String getStepName() {
        return "";
    }
}