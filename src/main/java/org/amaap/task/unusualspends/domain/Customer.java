package org.amaap.task.unusualspends.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Customer {

    int customerId;
    String customerName;
    String emailAddress;


    private Customer(int customerId, String customerName, String emailAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
    }
    public Customer(){}

    public static Customer createInstance(int customerId, String customerName, String emailAddress) {
        return new Customer(customerId, customerName, emailAddress);
    }

    public Customer createCustomer(int customerId, String customerName, String customerEmail) {
        return new Customer(customerId, customerName, customerEmail);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(customerName, customer.customerName) && Objects.equals(emailAddress, customer.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, emailAddress);
    }



}
