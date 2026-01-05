package com.example.Saga_Wallet.Service.Saga;


public interface SagaStep {

    boolean execute(SagaContext context);

    boolean compensate(SagaContext context);
    
    String getStepName();
}
