package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.service.TransactionRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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


}