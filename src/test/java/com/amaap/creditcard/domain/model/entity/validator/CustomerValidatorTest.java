package com.amaap.creditcard.domain.model.entity.validator;

import org.junit.jupiter.api.Test;

import static com.amaap.creditcard.domain.model.entity.validator.CustomerValidator.validate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerValidatorTest {

    CustomerValidator customerValidator = new CustomerValidator();

    @Test
    void shouldBeAbleToCreateCustomerValidator() {
        // arrange & act
        CustomerValidator customerValidator = new CustomerValidator();

        // assert
        assertNotNull(customerValidator);

    }

    @Test
    void shouldReturnTrueIfValidArgumentsHasBeenPassed() {
        // act & assert
        assertTrue(validate("Sudhir Takale", "sudhirtakale@gmail.com"));
        assertTrue(validate("Sudhir Hanmant Takale", "sudhirtakale111@gmail.com"));

    }

    @Test
    void shouldReturnFalseIfInvalidCustomerNamePassed() {
        // act & assert
        assertFalse(validate("", "sudhirtakale@gmail.com"));
        assertFalse(validate("sudhir34", "sudhirtakale@gmail.com"));
        assertFalse(validate("@takale", "sudhirtakale@gmail.com"));
        assertFalse(validate("Sudhir-- ", "sudhirtakale@gmail.com"));
        assertFalse(validate("000udhir takale", "sudhirtakale@gmail.com"));
        assertFalse(validate("234sudhirtakale", "sudhirtakale@gmail.com"));
        assertFalse(validate("ax# bh", "sudhirtakale@gmail.com"));
        assertFalse(validate("abc&&&", "sudhirtakale@gmail.com"));

    }


    @Test
    void shouldReturnFalseIfInvalidEmailAddressIsPassed() {
        // act & assert
        assertFalse(validate("Sudhir Takale", ""));
        assertFalse(validate("Sudhir Takale", "@sudhir"));
        assertFalse(validate("Sudhir Takale", "SUdhiR"));
        assertFalse(validate("Sudhir Takale", "gmail@sudhir@.com"));
        assertFalse(validate("Sudhir Takale", "----.com"));
        assertFalse(validate("Sudhir Takale", "111111111"));
        assertFalse(validate("Sudhir Takale", "kkkkkkkk"));
        assertFalse(validate("Sudhir Takale", "@@Sudhirtakale.com"));
        assertFalse(validate("Sudhir Takale", "123.com"));
        assertFalse(validate("Sudhir Takale", "babubhai@.com"));

    }


}