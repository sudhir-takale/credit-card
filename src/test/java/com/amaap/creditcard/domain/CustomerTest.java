package com.amaap.creditcard.domain;

import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.amaap.creditcard.domain.Customer.createNewCustomer;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {


    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange & Act
        Customer result = createNewCustomer(1, "Sudhir T", "Sudhirtakale99@gmail.com");
//        Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void shouldReturnInvalidCustomerIdExceptionIfIdIsLessThanEqualZero() {
//        Act & Assert
        assertThrows(InvalidCustomerIdException.class, () -> {
            createNewCustomer(0, "Sudhir T", "Sudhirtakale99@gmail.com");

        }, 0 + "id should not be 0");
    }

    @Test
    void shouldReturnInvalidCustomerIdExceptionIfIdIsLessThanZero() {
//        Act & Assert
        assertThrows(InvalidCustomerIdException.class, () -> {
            createNewCustomer(-1, "Sudhir T", "Sudhirtakale99@gmail.com");

        }, -1 + "id should not be 0");
    }

    @Test
    void shouldReturnInvalidNameExceptionIfNameIsEmpty() {
//        Act & Assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            createNewCustomer(1, "", "Sudhirtakale99@gmail.com");

        }, " " + "Name of the Customer should not be Empty!");

    }


    @Test
    void shouldReturnInvalidNameExceptionIfNameContainsNumbers() {
//        Act & Assert
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
//        Act & Assert
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            createNewCustomer(1, "John Doe", "");
        }, "Email address of the Customer should not be empty!");
    }

    @Test
    void shouldReturnInvalidEmailAddressExceptionIfEmailAddressIsInvalid() {
//        Act & Assert
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            createNewCustomer(2, "Alice", "invalid_email");
        }, "Invalid email address format!");
    }

    @Test
    void shouldCreateCustomerIfEmailAddressIsValid() {
//        Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            createNewCustomer(3, "Bob", "bob@example.com");
        });
    }

}
