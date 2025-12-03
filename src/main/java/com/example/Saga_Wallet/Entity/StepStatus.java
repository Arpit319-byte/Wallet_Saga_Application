package com.example.Saga_Wallet.Entity;

public enum StepStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    FAILED,
    COMPENSATING,
    COMPENSATED,
    SKIPPED,
}
