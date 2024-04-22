package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import com.amaap.creditcard.service.validator.CustomerValidator;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String emailAddress;

    public Customer(String name, String emailAddress) {

        this.name = name;
        this.emailAddress = emailAddress;
    }

    public static Customer create(String name, String emailAddress) throws InvalidCustomerDataException {
        if (!CustomerValidator.validate(name, emailAddress))
            throw new InvalidCustomerDataException("Invalid customer data has been passed");

        return new Customer(name, emailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, emailAddress);
    }
}
