package org.amaap.task.creditcard.domain;

import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerEmailException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerIdException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.amaap.task.creditcard.domain.validators.EmailValidator;
import org.amaap.task.creditcard.domain.validators.NameValidator;
import org.amaap.task.creditcard.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    List<Transaction> transactions;
    private int customerId;
    private String name;
    private String emailAddress;

    private Customer(int id, String name, String email) {
        this.customerId = id;
        this.name = name;
        this.emailAddress = email;
        transactions = new ArrayList<>();
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


}
