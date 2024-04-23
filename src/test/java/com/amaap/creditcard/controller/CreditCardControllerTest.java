package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.CreditCardService;
import com.amaap.creditcard.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardControllerTest {

    CustomerController customerController = new CustomerController(new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase())));

    CreditCardController creditCardController =
            new CreditCardController(new CreditCardService(new InMemoryCreditCardRepository(new FakeInMemoryDatabase()),
                    new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase()))));


    @Test
    void shouldBeAbleToCreateCreditCard() {
        // arrange
        int customerId = 1;
        customerController.create("Sudhir Takale", "sudhirtakaledmmy@gmail.com");
        Response expected = new Response(HttpsStatus.OK, "Creditcard created successfully");

        // act
        Response actual = creditCardController.create(1);

        // assert
        assertEquals(expected, actual);

    }

//    @Test
//    void shouldBeAbleToSaveCreditCard() {
//        // arrange
//        int customerId = 1;
//        customerController.create("Sudhir Takale", "sudhirtakaledmmy@gmail.com");
//        Response expected = new Response(HttpsStatus.OK, "Creditcard created successfully");
//
//        // act
//        Response actual = creditCardController.create(1);
//
//    }


}
