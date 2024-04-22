package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import com.amaap.creditcard.service.validator.CustomerValidator;

public class CustomerService {
    public void create(String name, String emailAddress) throws InvalidCustomerDataException {
        if (!CustomerValidator.validate(name, emailAddress))
            throw new InvalidCustomerDataException("Invalid customer data has been passed");
        Customer customer = new Customer(name, emailAddress);
    }
}
