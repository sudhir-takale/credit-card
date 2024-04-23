package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.service.TransactionRepository;

public class InMemoryTransactionRepository implements TransactionRepository {

    private InMemoryDatabase inMemoryDatabase;

    public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Transaction save(Transaction transaction) {

        return inMemoryDatabase.save(transaction);

    }
}
