package com.amaap.creditcard.domain.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameValidatorTest {

    @Test
    void shouldReturnTrueForValidName() {
        // Arrange
        String validName = "John Doe";
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
        String nameWithNumbers = "John123";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithNumbers));
    }

    @Test
    void shouldReturnFalseForNameWithSpecialCharacters() {
        // Arrange
        String nameWithSpecialCharacters = "John@Doe";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithSpecialCharacters));
    }

    @Test
    void shouldReturnFalseForNameWithBothNumbersAndSpecialCharacters() {
        // Arrange
        String nameWithNumbersAndSpecialCharacters = "John123@Doe";
        // Act & Assert
        assertFalse(NameValidator.validate(nameWithNumbersAndSpecialCharacters));
    }
}
