package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> getTransactions();


    Optional<Transaction> getTransactionFor(int transactionId);
}
