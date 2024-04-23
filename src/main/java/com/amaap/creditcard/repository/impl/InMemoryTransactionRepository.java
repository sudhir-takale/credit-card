package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.service.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTransactionRepository implements TransactionRepository {


    private InMemoryDatabase inMemoryDatabase;

    public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Transaction save(Transaction transaction) {

        return inMemoryDatabase.save(transaction);

    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = inMemoryDatabase.getTransactions();
        return transactions.isEmpty() ? new ArrayList<>() : transactions;
    }

    @Override
    public Optional<Transaction> getTransactionFor(int transactionId) {
        return inMemoryDatabase.getTransactionFor(transactionId);
    }
}
