package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.domain.service.TransactionAnalyzer;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.SpendService;
import com.amaap.creditcard.service.CreditCardService;
import com.amaap.creditcard.service.CustomerService;
import com.amaap.creditcard.service.TransactionService;
import com.amaap.creditcard.service.exception.InvalidEmailArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpendControllerTest {

    CreditCardService creditCardService =
            new CreditCardService(new InMemoryCreditCardRepository(new FakeInMemoryDatabase()),
                    new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase())));

    TransactionService transactionService =
            new TransactionService(new InMemoryTransactionRepository(new FakeInMemoryDatabase()));

    SpendController creditCardController =
            new SpendController(new SpendService(creditCardService, new TransactionAnalyzer(), transactionService));

    @Test
    void shouldBeAbleToCheckForAnUnusualSpendByCustomer() throws InvalidEmailArgumentException {
        // arrange
        Response expected = new Response(HttpsStatus.OK, "Success");

        // act
        Response actual = creditCardController.checkForUnusualSpend();

        // assert
        assertEquals(expected, actual);

    }


}
