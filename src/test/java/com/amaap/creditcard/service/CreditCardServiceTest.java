package com.amaap.creditcard.service;

import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class CreditCardServiceTest {

    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository(new FakeInMemoryDatabase()));
    CreditCardService creditCardService = new CreditCardService(new InMemoryCreditCardRepository(new FakeInMemoryDatabase()), customerService);

    @Test
    void shouldBeAbleToCreateNewCreditCard() throws InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        int customerId = 1;
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");

        // act
        boolean cardCreated = creditCardService.create(customerId);

        // assert
        assertTrue(cardCreated);
    }

    @Test
    void shouldThrowExceptionIfCustomerWithIdPassedNotFound() throws InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        int customerId = 14;
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("abs abs", "sudhirtakale@gmail.com");

        // assert
        assertThrows(CustomerNotFoundException.class, () -> creditCardService.create(customerId));

    }


    @Test
    void shouldThrowExceptionWhenInvalidCustomerIdHasPassed() throws InvalidCustomerDataException {
        // arrange
        int customerId = -14;
        customerService.create("Sudhir Takale", "sudhirtakale@gmail.com");
        customerService.create("abs abs", "sudhirtakale@gmail.com");

        // assert
        assertThrows(IllegalArgumentException.class, () -> creditCardService.create(customerId));

    }


}