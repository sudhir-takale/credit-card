package org.amaap.task.creditcard.domain;

import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerEmailException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerIdException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.amaap.task.creditcard.domain.Customer.createNewCustomer;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {


    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Customer result = createNewCustomer(1, "Sudhir T", "Sudhirtakale99@gmail.com");
        Assertions.assertNotNull(result);
    }

    @Test
    void shouldReturnInvalidCustomerIdExceptionIfIdIsLessThanEqualZero() {
        assertThrows(InvalidCustomerIdException.class, () -> {
            createNewCustomer(0, "Sudhir T", "Sudhirtakale99@gmail.com");

        }, 0 + "id should not be 0");

    }

    @Test
    void shouldReturnInvalidNameExceptionIfNameIsEmpty() {
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            createNewCustomer(1, "", "Sudhirtakale99@gmail.com");

        }, " " + "Name of the Customer should not be Empty!");

    }


    @Test
    void shouldReturnInvalidNameExceptionIfNameContainsNumbers() {
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            createNewCustomer(2, "John123", "john123@example.com");
        }, "Name of the Customer should not contain numbers!");
    }

    @Test
    void shouldReturnInvalidNameExceptionIfNameContainsSpecialCharacters() {
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            createNewCustomer(3, "John@Doe", "john.doe@example.com");
        }, "Name of the Customer should not contain special characters!");
    }

    @Test
    void shouldReturnInvalidEmailAddressExceptionIfEmailAddressIsEmpty() {
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            createNewCustomer(1, "John Doe", "");
        }, "Email address of the Customer should not be empty!");
    }

    @Test
    void shouldReturnInvalidEmailAddressExceptionIfEmailAddressIsInvalid() {
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            createNewCustomer(2, "Alice", "invalid_email");
        }, "Invalid email address format!");
    }

    @Test
    void shouldCreateCustomerIfEmailAddressIsValid() {
        Assertions.assertDoesNotThrow(() -> {
            createNewCustomer(3, "Bob", "bob@example.com");
        });
    }

}
