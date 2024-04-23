package com.amaap.creditcard.repository;

import com.amaap.creditcard.domain.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);

    List<Customer> getCustomers();

    Optional<Customer> getCustomerById(int id);
}
