package com.amaap.creditcard.domain.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameValidatorTest {

    @Test
    void shouldReturnTrueForValidName() {
        // Arrange
        String validName = "Sudhir Takale";
        // Act & Assert
        assertTrue(NameValidator.validate(validName));
    }

    @Test
    void shouldReturnFalseForEmptyName() {
        // Arrange
        String emptyName = "";
        // Act & Assert
        assertFalse(NameValidator.validate(emptyName));
    }

    @Test
    void shouldReturnFalseForNameWithNumbers() {
        // Arrange
        String nameWithNumbers = "shaurya234";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithNumbers));
    }

    @Test
    void shouldReturnFalseForNameWithSpecialCharacters() {
        // Arrange
        String nameWithSpecialCharacters = "sudhitakale@g.com";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithSpecialCharacters));
    }

    @Test
    void shouldReturnFalseForNameWithBothNumbersAndSpecialCharacters() {
        // Arrange
        String nameWithNumbersAndSpecialCharacters = "sudhir34@j";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithNumbersAndSpecialCharacters));
    }
}
