package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.Customer;

public class CreditCardManager {


    private final Customer customer;
    public CreditCardManager(Customer customer) {
        this.customer = customer;
    }

    public boolean createCustomer(int id, String name, String email) {
        return Customer.createNewCustomer(id, name, email);

    }


}
