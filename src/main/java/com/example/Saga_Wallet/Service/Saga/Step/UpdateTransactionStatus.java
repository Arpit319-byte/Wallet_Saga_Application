package com.example.Saga_Wallet.Service.Saga.Step;

import com.example.Saga_Wallet.Service.Saga.SagaContext;
import com.example.Saga_Wallet.Service.Saga.SagaStep;

public class UpdateTransactionStatus  implements SagaStep {
    @Override
    public boolean execute(SagaContext context) {
        return false;
    }

    @Override
    public boolean compensate(SagaContext context) {
        return false;
    }

    @Override
    public String getStepName() {
        return "";
    }
}
