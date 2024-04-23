package com.amaap.creditcard.service;

import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class CreditCardServiceTest {

    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase()));
    CreditCardService creditCardService = new CreditCardService();

    @Test
    void shouldBeAbleToCreateNewCreditCard() throws InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        int customerId = 1;
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("abs abs", "sudhirtakale@gmail.com");

        // act
        boolean card = creditCardService.create(1);

        // assert
        assertTrue(card);

    }

    @Test
    void shouldThrowExceptionIfCustomerNotFound() throws InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        int customerId = 14;
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("abs abs", "sudhirtakale@gmail.com");

        // assert
        assertThrows(CustomerNotFoundException.class, () -> creditCardService.create(customerId));

    }


}