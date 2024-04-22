package com.amaap.creditcard.repository;

import com.amaap.creditcard.domain.model.entity.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
}
