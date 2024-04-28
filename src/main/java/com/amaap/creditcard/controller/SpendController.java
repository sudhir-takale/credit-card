package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.service.SpendService;
import com.amaap.creditcard.service.exception.InvalidEmailArgumentException;
import com.google.inject.Inject;

public class SpendController {
    private final SpendService cardManagementService;

    @Inject
    public SpendController(SpendService cardManagementService) {
        this.cardManagementService = cardManagementService;
    }

    public Response checkForUnusualSpend() throws InvalidEmailArgumentException {
        cardManagementService.checkForUnusualSpend();
        return new Response(HttpsStatus.OK, "Success");
    }
}
