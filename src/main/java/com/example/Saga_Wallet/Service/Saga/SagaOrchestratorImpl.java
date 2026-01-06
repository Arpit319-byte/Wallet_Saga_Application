package com.example.Saga_Wallet.Service.Saga;

import com.example.Saga_Wallet.Entity.SagaInstance;

public class SagaOrchestratorImpl implements SagaOrchestrator{
    @Override
    public Long startSaga(SagaContext context) {
        return 0L;
    }

    @Override
    public boolean executeStep(Long sagaInstanceId, String stepName) {
        return false;
    }

    @Override
    public boolean compensateStep(Long sagaInstanceId, String stepName) {
        return false;
    }

    @Override
    public SagaInstance getSagaInstance(Long sagaInstanceId) {
        return null;
    }

    @Override
    public void compensateSaga(Long sagaInstanceId) {

    }

    @Override
    public void failSaga(Long sagaInstanceId) {

    }

    @Override
    public void completeSaga(Long sagaInstanceId) {

    }
}
