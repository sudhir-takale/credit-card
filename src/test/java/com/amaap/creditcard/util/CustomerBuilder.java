package com.amaap.creditcard.util;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;

public class CustomerBuilder {

    public Customer getCustomer() throws InvalidCustomerDataException {

        return Customer.create("Sudhir Takale", "sudhirtakale@gamil.com");

    }


}
