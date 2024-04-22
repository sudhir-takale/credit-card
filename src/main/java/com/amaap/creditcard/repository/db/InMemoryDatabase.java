package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.Customer;

public interface InMemoryDatabase {
    Customer add(Customer customer);
}
