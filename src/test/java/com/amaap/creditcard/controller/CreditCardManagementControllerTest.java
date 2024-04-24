package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardManagementControllerTest {

    CreditCardManagementController creditCardController = new CreditCardManagementController();

    @Test
    void shouldBeAbleToCheckForAnUnusualSpendByCustomer() {
        // arrange
        Response expected = new Response(HttpsStatus.OK, "Success");

        // act
        Response actual = creditCardController.checkForUnusualSpend();

        // assert
        assertEquals(expected, actual);

    }


}
