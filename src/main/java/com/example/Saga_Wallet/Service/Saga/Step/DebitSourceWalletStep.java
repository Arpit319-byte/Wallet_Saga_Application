package com.example.Saga_Wallet.Service.Saga.Step;

import com.example.Saga_Wallet.Entity.Wallet;
import com.example.Saga_Wallet.Repository.WalletRepository;
import com.example.Saga_Wallet.Service.Saga.SagaContext;
import com.example.Saga_Wallet.Service.Saga.SagaStep;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public class DebitSourceWalletStep implements SagaStep {

    private WalletRepository walletRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {

        Long fromWalletId=context.getLong("fromWalletId");
        BigDecimal amount=context.getBigDecimal("amount");

        Wallet wallet=walletRepository.findWalletByLock(fromWalletId).orElseThrow(()->new RuntimeException("Wallet not found"));
        wallet.debit(amount);

        context.put("fromWalletAfterDebit",wallet);
        return false;
    }

    @Override
    @Transactional
    public boolean compensate(SagaContext context) {

        Long fromWalletId=context.getLong("fromWalletId");
        BigDecimal amount=context.getBigDecimal("amount");

        Wallet wallet=walletRepository.findWalletByLock(fromWalletId).orElseThrow(()->new RuntimeException("Wallet not found"));
        wallet.credit(amount);

        context.put("fromWalletAfterDebitCompensate",wallet);
        return true;

    }

    @Override
    public String getStepName() {
        return "DebitSourceWalletStep";
    }
}
