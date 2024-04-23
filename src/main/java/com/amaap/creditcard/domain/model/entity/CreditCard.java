package com.amaap.creditcard.domain.model.entity;

import java.util.Objects;

public class CreditCard {
    private int id;
    private Customer customer;

    public CreditCard(Customer customer) {
        this.customer = customer;
    }


    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer);
    }
}
