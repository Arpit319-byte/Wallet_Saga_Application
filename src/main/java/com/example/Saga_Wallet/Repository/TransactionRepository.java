package com.example.Saga_Wallet.Repository;

import com.example.Saga_Wallet.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromWalletId(Long fromWalletId);

    List<Transaction> findByToWalletId(Long toWalletId);

    @Query("SELECT FROM transaction t WHERE t.fromWalletId= :walletId OR t.toWalletId= :walletId ")
    List<Transaction> findByWalletId(@Param("wallet_id") Long walletId);
}
