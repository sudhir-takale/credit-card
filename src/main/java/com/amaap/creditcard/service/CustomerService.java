package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String emailAddress) throws InvalidCustomerDataException {

        Customer customer = Customer.create(name, emailAddress);
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }
}
