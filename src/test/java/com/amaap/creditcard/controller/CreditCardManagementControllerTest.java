package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.CreditCardManagementService;
import com.amaap.creditcard.service.CreditCardService;
import com.amaap.creditcard.service.CustomerService;
import com.amaap.creditcard.service.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardManagementControllerTest {

    CreditCardService creditCardService =
            new CreditCardService(new InMemoryCreditCardRepository(new FakeInMemoryDatabase()),
                    new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase())));

    TransactionService transactionService =
            new TransactionService(new InMemoryTransactionRepository(new FakeInMemoryDatabase()));

    CreditCardManagementController creditCardController = new CreditCardManagementController(new CreditCardManagementService());

    @Test
    void shouldBeAbleToCheckForAnUnusualSpendByCustomer() {
        // arrange
        Response expected = new Response(HttpsStatus.OK, "Success");

        // act
        Response actual = creditCardController.checkForUnusualSpend();

        // assert
        assertEquals(expected, actual);

    }


}
