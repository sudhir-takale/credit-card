package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.TransactionService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TransactionControllerTest {

    TransactionController transactionController =
            new TransactionController(new TransactionService(new InMemoryTransactionRepository(new FakeInMemoryDatabase())));

    @Test
    void shouldBeAbleToCreateTransaction() {
        // arrange
        int cardId = 1;
        double amount = 34.23;
        LocalDate date = LocalDate.now();
        Category category = Category.TRAVEL;
        Response expected = new Response(HttpsStatus.OK, "Transaction created");

        // act
        Response actual = transactionController.createTransaction(cardId, date, category, amount);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetAllTransactions() {

    }


}