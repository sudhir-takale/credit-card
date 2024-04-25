package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TransactionTest {


    @Test
    void shouldBeAbleToCreateTransaction() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        LocalDate date = LocalDate.now();
        double amount = 34.0;
        Category category = Category.TRAVEL;

        // act
        Transaction transaction = Transaction.create(cardId, date, category, amount);

        // assert
        assertEquals(1, transaction.getCardId());
        assertEquals(LocalDate.now(), date);

    }

    @Test
    void shouldBeAbleToTransactionId() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        LocalDate date = LocalDate.now();
        double amount = 34.0;
        Category category = Category.TRAVEL;

        // act
        Transaction transaction = Transaction.create(cardId, date, category, amount);
        transaction.setId(23);

        // assert
        assertEquals(23, transaction.getId());
    }

    @Test
    void shouldBeAbleToGetTransactionDate() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        LocalDate date = LocalDate.now();
        double amount = 34.0;
        Category category = Category.TRAVEL;

        // act
        Transaction transaction = Transaction.create(cardId, date, category, amount);

        // assert
        assertEquals(LocalDate.now(), date);
    }


    @Test
    void shouldBeAbleToGetCategoryOfTransaction() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        LocalDate date = LocalDate.now();
        double amount = 34.0;
        Category category = Category.TRAVEL;

        // act
        Transaction transaction = Transaction.create(cardId, date, category, amount);

        // assert
        assertEquals(Category.TRAVEL, transaction.getCategory());
    }

    @Test
    void shouldBeAbleGetAmountOfTransaction() throws InvalidTransactionParameters {
        // arrange
        int cardId = 1;
        LocalDate date = LocalDate.now();
        double amount = 34.0;
        Category category = Category.TRAVEL;

        // act
        Transaction transaction = Transaction.create(cardId, date, category, amount);

        // assert
        assertEquals(34.0, amount);
    }
}