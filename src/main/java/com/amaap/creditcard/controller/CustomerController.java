package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.service.CustomerService;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Response create(String name, String emailAddress) {
        try {
            customerService.create(name, emailAddress);
            return new Response(HttpsStatus.OK, "New customer created successfully");
        } catch (InvalidCustomerDataException e) {
            return new Response(HttpsStatus.BADREQUEST, "Failed to create Customer");
        }
    }

    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
}
