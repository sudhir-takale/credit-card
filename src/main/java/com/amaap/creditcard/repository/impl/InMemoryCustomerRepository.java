package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.repository.db.InMemoryDatabase;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Customer save(Customer customer) {
        return inMemoryDatabase.add(customer);
    }
}
