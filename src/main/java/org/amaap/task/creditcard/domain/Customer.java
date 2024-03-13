package org.amaap.task.creditcard.domain;

import org.amaap.task.creditcard.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int customerId;
    private String name;
    private String emailAddress;

    private Customer(int id, String name, String email) {
        this.customerId = id;
        this.name = name;
        this.emailAddress = email;
        List<Transaction> transactions = new ArrayList<>();
    }



    public static boolean createNewCustomer(int id, String name, String email) {
        Customer customer = new Customer(id, name, email);
        return true;
    }
}
