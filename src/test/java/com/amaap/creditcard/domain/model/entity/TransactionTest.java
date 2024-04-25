package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


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



    @Test
    public void ShouldReturnTrueForEqualTransactions() throws InvalidTransactionParameters {
        // arrange
        LocalDate date = LocalDate.of(2024, 4, 25);

        // act
        Transaction transaction1 = Transaction.create(123456, date, Category.GROCERY, 100.0);
        Transaction transaction2 = Transaction.create(123456, date, Category.GROCERY, 100.0);

        // assert
        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    public void shouldReturnFalseForDifferentCardIds() throws InvalidTransactionParameters {
        // arrange
        LocalDate date = LocalDate.of(2024, 4, 25);

        // act
        Transaction transaction1 = Transaction.create(123456, date, Category.GROCERY, 100.0);
        Transaction transaction2 = Transaction.create(987654, date, Category.GROCERY, 100.0);

        // assert
        assertFalse(transaction1.equals(transaction2));
    }

    @Test
    public void shouldReturnFalseForDifferentDates() throws InvalidTransactionParameters {
        // arrange & act
        LocalDate date1 = LocalDate.of(2024, 4, 25);
        LocalDate date2 = LocalDate.of(2024, 4, 26);
        Transaction transaction1 = Transaction.create(123456, date1, Category.GROCERY, 100.0);
        Transaction transaction2 = Transaction.create(123456, date2, Category.GROCERY, 100.0);

        // assert
        assertFalse(transaction1.equals(transaction2));
    }


    @Test
    public void shouldReturnFalseForNullObject() throws InvalidTransactionParameters {
        // arrange
        LocalDate date = LocalDate.of(2024, 4, 25);

        // act
        Transaction transaction = Transaction.create(123456, date, Category.GROCERY, 100.0);

        // assert
        assertFalse(transaction.equals(null));
    }

    @Test
    public void shouldReturnFalseForDifferentClasses() throws InvalidTransactionParameters {
        // arrange
        LocalDate date = LocalDate.of(2024, 4, 25);

        // act
        Transaction transaction = Transaction.create(123456, date, Category.GROCERY, 100.0);

        // assert
        assertFalse(transaction.equals("123456"));
    }

    @Test
    public void shouldReturnConsistentHashCode() throws InvalidTransactionParameters {
        // arrange
        LocalDate date = LocalDate.of(2024, 4, 25);
        Transaction transaction = Transaction.create(123456, date, Category.GROCERY, 100.0);

        // act
        int hashCode1 = transaction.hashCode();
        int hashCode2 = transaction.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

}