package com.amaap.creditcard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardManagementServiceTest {

    CreditCardManagementService cardManagementService = new CreditCardManagementService();
    @Test
    void shouldBeAbleToCheckForUnusualSpendByTheCustomer() {
        // act
        boolean result = cardManagementService.checkForUnusualSpend();

        // assert
        assertTrue(result);

    }


}