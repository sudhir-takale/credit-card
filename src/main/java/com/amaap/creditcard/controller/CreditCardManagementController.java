package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;

public class CreditCardManagementController {

    public Response checkForUnusualSpend() {
        return new Response(HttpsStatus.OK, "Success");
    }
}
