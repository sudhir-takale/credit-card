package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class TransactionServiceTest {


    TransactionService transactionService = new TransactionService(new InMemoryTransactionRepository(new FakeInMemoryDatabase()));

    @Test
    void shouldBeAbleToCreateTransaction() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        double amount = 34.23;
        LocalDate date = LocalDate.now();
        Category category = Category.TRAVEL;
        Transaction expected = Transaction.create(cardId, date, category, amount);
        // act
        Transaction actual = transactionService.createTransaction(cardId, date, category, amount);

        // assert
        assertEquals(expected, actual);

    }


    @Test
    void shouldThrowExceptionWhenInvalidArgumentsPassed() {
        // arrange
        int cardId = -1;
        double amount = 34.23;
        LocalDate date = LocalDate.now();
        Category category = Category.TRAVEL;

        // assert
        assertThrows(InvalidTransactionParameters.class, () -> transactionService.createTransaction(cardId, date, category, amount));
    }

}