package com.amaap.creditcard.service.validator;

import com.amaap.creditcard.service.communication.validator.AlertValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlertValidatorTest {


    @Test
    void shouldBeAbleToCreateInstanceOfEmailValidator() {
        // arrange & act
        AlertValidator alertValidator = new AlertValidator();

        // assert
        assertNotNull(alertValidator);
    }

    @Test
    void shouldReturnFalseWhenInvalidEmailAddressIsPassed() {
        // assert
        assertFalse(AlertValidator.validate("message body", "sdd"));
        assertFalse(AlertValidator.validate("message body", "223sdd"));
        assertFalse(AlertValidator.validate("message body", "sudhir    "));
        assertFalse(AlertValidator.validate("message body", "sudhir@jk"));
        assertFalse(AlertValidator.validate("message body", "sudhir3"));
        assertFalse(AlertValidator.validate("message body", ""));
    }

    @Test
    void shouldReturnFalseWhenInvalidSubjectIsPassed() {
        // assert
        assertFalse(AlertValidator.validate("", "sdd"));
        assertFalse(AlertValidator.validate("", "sdd"));
    }

    @Test
    void shouldReturnTrueIfValidParameterHasPassed() {
        // assert
        assertTrue(AlertValidator.validate("I am here", "sudhirtakale99@gmail.com"));

    }


}