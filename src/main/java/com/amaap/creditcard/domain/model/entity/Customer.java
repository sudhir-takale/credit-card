package com.amaap.creditcard.domain.model.entity;

public class Customer {
    private int id;
    private String name;
    private String emailAddress;

    public Customer(String name, String emailAddress) {

        this.name = name;
        this.emailAddress = emailAddress;
    }
}
