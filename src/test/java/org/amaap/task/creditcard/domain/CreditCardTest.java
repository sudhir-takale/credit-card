package org.amaap.task.creditcard.domain;

import org.amaap.task.creditcard.domain.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreditCardTest {

    @Test
    void shouldAbleToCreateCreditCard() throws InvalidCardNumberException, NullCustomerException, InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        CreditCard creditCard = new CreditCard();
        boolean result = creditCard.createNewCreditCard(1212, Customer.createNewCustomer(1, "Sudhir T", "Demo@gmail.com"));
        Assertions.assertTrue(result);
    }

    @Test
    void shouldThrowAnExceptionIfCreditCardNumberIfZero() {
        CreditCard creditCard = new CreditCard();
        Assertions.assertThrows(InvalidCardNumberException.class, () -> {
            creditCard.createNewCreditCard(0, Customer.createNewCustomer(1, "Sudhir T", "Demo@gmail.com"));

        }, 0 + "Card Number is Invalid !");

    }

    @Test
    void shouldReturnExceptionIfCustomerIsNull() {
        CreditCard creditCard = new CreditCard();
        Assertions.assertThrows(InvalidCardNumberException.class, () -> {
            creditCard.createNewCreditCard(1212, null);

        }, null + "Customer is null !");


    }


}