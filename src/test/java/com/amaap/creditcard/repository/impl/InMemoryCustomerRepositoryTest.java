package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    void shouldBeAbleToGetAllCustomers() throws InvalidCustomerDataException {
        // arrange
        inMemoryCustomerRepository.save(Customer.create("Sudhir Takale", "sudhirtakale99@gmail.com"));
        inMemoryCustomerRepository.save(Customer.create("Baburao apte", "babubhai@gmail.com"));
        inMemoryCustomerRepository.save(Customer.create("Raju apte", "raju@gmail.com"));

        // act
        List<Customer> allCustomers = inMemoryCustomerRepository.getCustomers();

        // assert
        assertEquals(3, allCustomers.size());

    }

    @Test
    void shouldBeAbleToGetCustomerOfProvidedId() throws InvalidCustomerDataException {
        // arrange
        inMemoryCustomerRepository.save(Customer.create("Sudhir Takale", "sudhirtakale99@gmail.com"));

        // act
        Optional<Customer> customer = inMemoryCustomerRepository.getCustomerById(1);

        // assert
        assertTrue(customer.isPresent());
        assertEquals(1, customer.get().getId());
        assertEquals("Sudhir Takale", customer.get().getName());
        assertEquals("sudhirtakale99@gmail.com", customer.get().getEmailAddress());

    }


}