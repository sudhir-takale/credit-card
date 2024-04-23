package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
