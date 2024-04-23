package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.domain.model.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface InMemoryDatabase {
    Customer add(Customer customer);

    List<Customer> getCustomers();

    Optional<Customer> getCustomerById(int id);

    int insert(CreditCard card);

    Transaction save(Transaction transaction);
}
