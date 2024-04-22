package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static com.amaap.creditcard.domain.model.entity.Customer.create;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerServiceTest {
    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository());

    @Test
    void shouldBeAbleToCreateANewCustomer() throws InvalidCustomerDataException {
        // arrange
        String name = "sudhir takale";
        String email = "dummyemail@gmail.com";

        // act
        Customer expected = create(name, email);
        Customer actual = customerService.create(name, email);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldThrowExceptionIfInvalidEmailIsPassed() {
        // arrange
        String name = "sudhir takale";
        String email = "dummyemail@gmail.3243";

        // assert
        Throwable message = assertThrows(InvalidCustomerDataException.class, () -> customerService.create(name, email));

    }

    @Test
    void shouldThrowExceptionIfInvalidNameIsPassed() {
        // arrange
        String name = "sudhir takale434";
        String email = "dummyemail@gmail.com";

        // assert
        Throwable message = assertThrows(InvalidCustomerDataException.class, () -> customerService.create(name, email));
    }

    @Test
    void shouldBeAbleToSaveCustomerToRepository() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("Sudhir Takale", "dummyemail@gmail.com");

        // act
        Customer actualCustomer = customerService.create("Sudhir Takale", "dummyemail@gmail.com");

        // assert
        assertEquals(customer, actualCustomer);

    }


}