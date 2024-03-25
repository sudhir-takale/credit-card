package com.amaap.creditcard.alerts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailValidatorTest {

    @Test
    void shouldReturnTrueIfValidArgumentsPassed() {
        assertTrue(EmailValidator.validate("Subject", "Content", "sudhir@.com"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfSubjectIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validate("", "Content", "sudhir@.com");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfContentIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validate("Subject", "", "sudhir@.com");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfRecipientsEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validate("Subject", "Content", "");
        });
    }

    @Test
    void shouldNotThrowIfValidArgumentsPassed() {
        assertDoesNotThrow(() -> {
            EmailValidator.validateEmail("Subject", "Body", "sudhir@gmail.com");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfSubjectIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validateEmail(null, "Test Body", "sudhir@.com");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfBodyIsEmpty(){
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validateEmail("Subject", "", "sudhir@.com");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmailIsNotValid() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validateEmail("Subject", "Body", "invalid_email");
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailValidator.validateEmail("Subject", " Body", "");
        });
    }
}
