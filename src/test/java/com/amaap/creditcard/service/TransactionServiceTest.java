package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.exception.TransactionNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidTransactionParameters {
        // arrange
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 45.5);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 34.4);

        // act
        List<Transaction> transactions = transactionService.getTransactions();

        // assert
        assertEquals(2, transactions.size());
    }

    @Test
    void shouldBeAbleToGetTransactionWithId() throws InvalidTransactionParameters, TransactionNotFoundException {
        // arrange
        int transactionId = 1;
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 45.5);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 34.4);

        // act
        Transaction transaction = transactionService.getTransactionFor(transactionId);

        // assert
        assertEquals(1, transaction.getId());
    }

    @Test
    void shouldThrowExceptionWhenTransactionNotFound() throws InvalidTransactionParameters {
        // arrange
        int transactionId = 14;
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 45.5);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 34.4);

        // assert
        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionFor(14));
    }

}