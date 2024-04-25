package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.google.inject.Inject;

import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
    public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Customer save(Customer customer) {
        return inMemoryDatabase.add(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return inMemoryDatabase.getCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return inMemoryDatabase.getCustomerById(id);
    }
}
