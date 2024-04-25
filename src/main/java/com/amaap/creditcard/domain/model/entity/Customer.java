package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import com.amaap.creditcard.domain.model.entity.validator.CustomerValidator;

import java.util.Objects;

import static com.amaap.creditcard.domain.model.entity.validator.CustomerValidator.validate;

public class Customer {
    private int id;
    private String name;
    private String emailAddress;

    public Customer(String name, String emailAddress) {

        this.name = name;
        this.emailAddress = emailAddress;
    }

    public static Customer create(String name, String emailAddress) throws InvalidCustomerDataException {
        if (!validate(name, emailAddress))
            throw new InvalidCustomerDataException("Invalid customer data has been passed");

        return new Customer(name, emailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailAddress);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "emailAddress='" + emailAddress + '\'' + ", id=" + id + ", name='" + name + '\'' + '}';
    }
}
