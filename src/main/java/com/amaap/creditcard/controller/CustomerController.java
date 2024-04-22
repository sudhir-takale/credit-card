package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;

public class CustomerController {
    public Response create(String name, String emailAddress) {
        return new Response(HttpsStatus.OK, "New customer created successfully");
    }
}
