package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.domain.model.entity.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCreditCardRepositoryTest {

    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToSaveCreditCard() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("Sudhir Takale", "abs@gmail.com");
        CreditCard creditCard = new CreditCard(customer);

        // act
        int rows = creditCardRepository.save(creditCard);

        // assert
        assertEquals(1, rows);

    }

}