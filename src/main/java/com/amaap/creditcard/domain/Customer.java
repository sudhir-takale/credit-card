package com.amaap.creditcard.domain;

import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import com.amaap.creditcard.domain.validators.EmailValidator;
import com.amaap.creditcard.domain.validators.NameValidator;


public class Customer {

    private int customerId;
    private String name;
    private String emailAddress;

    private Customer(int id, String name, String email) {
        this.customerId = id;
        this.name = name;
        this.emailAddress = email;
    }


    public static Customer createNewCustomer(int id, String name, String email) throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        if (!validateId(id)) {
            throw new InvalidCustomerIdException(id + "id should not be zero");
        }
        if (!NameValidator.validate(name)) {
            throw new InvalidCustomerNameException(name + "Name of the Customer should not be Empty!");
        }
        if (!EmailValidator.validate(email)) {
            throw new InvalidCustomerEmailException(email + "Email should be correct!");
        }

        return new Customer(id, name, email);
    }


    private static boolean validateId(int id) throws InvalidCustomerIdException {
        if (id <= 0) {
            throw new InvalidCustomerIdException(id + "id should not be zero");

        }
        return true;
    }


    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


}
