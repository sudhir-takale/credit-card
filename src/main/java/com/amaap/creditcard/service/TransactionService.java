package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;

import java.time.LocalDate;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(int cardId, LocalDate date, Category category, double amount) throws InvalidTransactionParameters {

        Transaction transaction = Transaction.create(cardId, date, category, amount);
        return transactionRepository.save(transaction);

    }
}
