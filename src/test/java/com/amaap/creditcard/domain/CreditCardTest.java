package com.amaap.creditcard.domain;

import com.amaap.creditcard.domain.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreditCardTest {

    @Test
    void shouldAbleToCreateCreditCard() throws InvalidCardNumberException, NullCustomerException, InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange
        CreditCard creditCard = new CreditCard();
//        Act
        boolean result = creditCard.createNewCreditCard(1212, Customer.createNewCustomer(1, "Sudhir T", "Demo@gmail.com"));
//        Assert
        Assertions.assertTrue(result);
    }

    @Test
    void shouldThrowAnExceptionIfCreditCardNumberIfZero() {
//        Arrange
        CreditCard creditCard = new CreditCard();
//        Act & assert
        Assertions.assertThrows(InvalidCardNumberException.class, () -> creditCard.createNewCreditCard(0, Customer.createNewCustomer(1, "Sudhir T", "Demo@gmail.com")), 0 + "Card Number is Invalid !");

    }

    @Test
    void shouldReturnExceptionIfCustomerIsNull() {
//        Arrange
        CreditCard creditCard = new CreditCard();
//        Act & assert
        Assertions.assertThrows(NullCustomerException.class, () -> creditCard.createNewCreditCard(1212, null));

    }
}