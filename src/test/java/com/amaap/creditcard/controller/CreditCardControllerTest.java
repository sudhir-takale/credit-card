package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.CreditCardService;
import com.amaap.creditcard.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CreditCardControllerTest {

    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase()));
    CustomerController customerController = new CustomerController(customerService);

    CreditCardController creditCardController = new CreditCardController(new CreditCardService(new InMemoryCreditCardRepository(new FakeInMemoryDatabase()), customerService));


    @Test
    void shouldBeAbleToCreateCreditCard() {
        // arrange
        int customerId = 1;
        customerController.create("Sudhir Takale", "sudhirtakaledmmy@gmail.com");
        Response expected = new Response(HttpsStatus.OK, "Credit card created successfully");

        // act
        Response actual = creditCardController.create(1);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldNotCreateCreditCardIfInvalidCustomerIdHasPassed() {
        // arrange
        int customerId = 13;
        customerController.create("Sudhir Takale", "sudhirtakaledmmy@gmail.com");
        Response expected = new Response(HttpsStatus.BADREQUEST, "Customer doesn't exist");

        // act
        Response actual = creditCardController.create(customerId);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldNotCreateCreditCardIfCustomerIdIsLessThanZeroIsPassed() {
        // arrange
        int customerId = -13;

        // assert
        assertThrows(IllegalArgumentException.class, () -> creditCardController.create(customerId));

    }


}
