package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCustomerRepositoryTest {
    InMemoryCustomerRepository inMemoryCustomerRepository = new InMemoryCustomerRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToSaveCustomerToCustomerTable() throws InvalidCustomerDataException {
        // arrange
        String name = "Sudhir Takale";
        String email = "abcdef@gmail.com";
        Customer expected = Customer.create(name, email);

        // act
        Customer actual = inMemoryCustomerRepository.save(expected);

        // assert
        assertEquals(expected, actual);
    }


}