package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CustomerRepository;

public class InMemoryCustomerRepository implements CustomerRepository {

    @Override
    public Customer save(Customer customer) {

        return customer;
    }
}
