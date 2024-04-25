package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.service.CreditCardService;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.google.inject.Inject;

public class CreditCardController {

    private final CreditCardService creditCardService;

    @Inject
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response create(int customerId) {
        try {
            creditCardService.create(customerId);
            return new Response(HttpsStatus.OK, "Credit card created successfully");
        } catch (CustomerNotFoundException e) {
            return new Response(HttpsStatus.BADREQUEST, "Customer doesn't exist");
        }
    }
}
