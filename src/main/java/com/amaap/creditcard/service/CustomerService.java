package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String emailAddress) throws InvalidCustomerDataException {

        Customer customer = Customer.create(name, emailAddress);
        return customerRepository.save(customer);
    }

}
