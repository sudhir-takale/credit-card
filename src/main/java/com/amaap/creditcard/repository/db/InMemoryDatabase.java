package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface InMemoryDatabase {
    Customer add(Customer customer);

    List<Customer> getCustomers();

    Optional<Customer> getCustomerById(int id);
}
