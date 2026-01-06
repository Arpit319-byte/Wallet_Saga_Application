package com.example.Saga_Wallet.Service.Saga.Step;

import com.example.Saga_Wallet.Entity.Transaction;
import com.example.Saga_Wallet.Entity.TransactionStatus;
import com.example.Saga_Wallet.Repository.TransactionRepository;
import com.example.Saga_Wallet.Service.Saga.SagaContext;
import com.example.Saga_Wallet.Service.Saga.SagaStep;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateTransactionStatus implements SagaStep {

    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {
        Long transactionId = context.getLong("transactionId");
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("transaction not found"));

        context.put("originalTransactionStatus", transaction.getStatus());

        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        context.put("transactionStatusAfterUpdate", TransactionStatus.SUCCESS);
        return true;
    }

    @Override
    @Transactional
    public boolean compensate(SagaContext context) {

        Long transactionId = context.getLong("transactionId");
        TransactionStatus originalStatus = TransactionStatus.valueOf(context.getString("originalTransactionStatus"));

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("transaction not found"));

        transaction.setStatus(originalStatus);
        transactionRepository.save(transaction);

        return true;
    }

    @Override
    public String getStepName() {
        return "";
    }
}
