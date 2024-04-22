package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    int customerIdCounter = 0;
    private List<Customer> customerTable = new ArrayList<>();

    @Override
    public Customer add(Customer customer) {
        customer.setId(++customerIdCounter);
        customerTable.add(customer);
        return customer;
    }
}
