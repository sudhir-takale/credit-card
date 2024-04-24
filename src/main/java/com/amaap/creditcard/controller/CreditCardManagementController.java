package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.service.CreditCardManagementService;
import com.google.inject.Inject;

public class CreditCardManagementController {
    private final CreditCardManagementService cardManagementService;

    @Inject
    public CreditCardManagementController(CreditCardManagementService cardManagementService) {
        this.cardManagementService = cardManagementService;
    }

    public Response checkForUnusualSpend() {
        cardManagementService.checkForUnusualSpend();
        return new Response(HttpsStatus.OK, "Success");

    }
}
