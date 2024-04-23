package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.service.TransactionRepository;
import com.amaap.creditcard.util.TransactionBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

class InMemoryTransactionRepositoryTest {
    TransactionRepository transactionRepository = new InMemoryTransactionRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToSaveTransaction() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        double amount = 34.23;
        LocalDate date = LocalDate.now();
        Category category = Category.TRAVEL;
        Transaction expected = Transaction.create(cardId, date, category, amount);

        // act
        Transaction actual = transactionRepository.save(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidTransactionParameters {
        // arrange
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());

        // act
        List<Transaction> transactions = transactionRepository.getTransactions();

        // assert
        assertEquals(4, transactions.size());
    }

    @Test
    void shouldReturnEmptyListIfNoTransactionsPresent() {
        // act
        List<Transaction> transactions = transactionRepository.getTransactions();

        // assert
        assertEquals(0, transactions.size());
    }

    @Test
    void shouldBeAbleToGetTransactionWithId() throws InvalidTransactionParameters {
        // arrange
        int transactionId = 1;
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());
        transactionRepository.save(TransactionBuilder.createTransaction());

        // act
        Optional<Transaction> transaction = transactionRepository.getTransactionFor(transactionId);

        // assert
        assertEquals(1, transaction.get().getId());
    }

}