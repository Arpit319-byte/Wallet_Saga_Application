package com.example.Saga_Wallet.Repository;

import com.example.Saga_Wallet.Service.Saga.SagaStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SagaStepRepository extends JpaRepository<SagaStep, Long> {

    List<SagaStep> findByTransactionId(Long sagaInstanceId);

    @Query("SELECT * FROM SagaStep s WHERE s.sagaInstanceId= :sagaInstanceId AND s.sagaStatus='COMPLETED'" )
    List<SagaStep> findCompletedSagaStepsByInstanceId(@Param("sagaInstanceId") Long sagaInstanceId);

    @Query("SELECT * FROM SagaStep s WHERE s.sagaInstanceId= :sagaInstanceId AND s.sagaStatus IN ('COMPLETED','COMPENSATED')")
    List<SagaStep> findCompletedOrCompensatedSagaStepsByInstanceId(@Param("sagaInstanceId")Long sagaInstanceId);
}
