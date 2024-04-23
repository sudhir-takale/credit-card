package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    private final List<Customer> customerTable = new ArrayList<>();
    int customerIdCounter = 0;

    @Override
    public Customer add(Customer customer) {
        customer.setId(++customerIdCounter);
        customerTable.add(customer);
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customerTable;
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customerTable.stream().filter(customer -> customer.getId() == id).findFirst();
    }
}
