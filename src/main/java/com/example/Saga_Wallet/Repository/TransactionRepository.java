package com.example.Saga_Wallet.Repository;

import com.example.Saga_Wallet.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
