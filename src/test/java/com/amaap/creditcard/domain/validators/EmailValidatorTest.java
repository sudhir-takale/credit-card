package com.amaap.creditcard.domain.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    @Test
    void shouldReturnTrueForValidEmail() {
        // Arrange
        String validEmail = "sudhirtakale@gmail.com";
        // Act & Assert
        assertTrue(EmailValidator.validate(validEmail));
    }

    @Test
    void shouldReturnFalseForEmptyEmail() {
        // Arrange
        String emptyEmail = "";
        // Act & Assert
        assertFalse(EmailValidator.validate(emptyEmail));
    }

    @Test
    void shouldReturnFalseForInvalidEmail() {
        // Arrange
        String invalidEmail = "test.com";
        // Act & Assert
        assertFalse(EmailValidator.validate(invalidEmail));
    }

    @Test
    void shouldReturnFalseForEmailWithoutAtSymbol() {
        // Arrange
        String emailWithoutAtSymbol = "test.com";
        // Act & Assert
        assertFalse(EmailValidator.validate(emailWithoutAtSymbol));
    }

}
