package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void shouldBeAbleToCreateCustomerWithValidArguments() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("sudhir Takale", "sudhirtakale@gmail.com");

        // assert
        assertEquals("sudhir Takale", customer.getName());
        assertEquals("sudhirtakale@gmail.com", customer.getEmailAddress());
    }

    @Test
    void shouldThrowExceptionWhenInvalidArgumentsPassed() {
        // assert
        assertThrows(InvalidCustomerDataException.class, () -> Customer.create("", "sudhir@gmail.com"));
        assertThrows(InvalidCustomerDataException.class, () -> Customer.create("Sudhir Taklae", "sudhir.com"));
    }

}