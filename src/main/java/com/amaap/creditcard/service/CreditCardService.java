package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;

import java.util.Optional;

public class CreditCardService {
    private final CustomerService customerService;
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository, CustomerService customerService) {
        this.creditCardRepository = creditCardRepository;
        this.customerService = customerService;
    }

    public boolean create(int customerId) throws CustomerNotFoundException {
        Optional<Customer> existingCustomer = customerService.getCustomerById(customerId);
        if (existingCustomer.isPresent()) {
            CreditCard card = new CreditCard(existingCustomer.get());
            creditCardRepository.save(card);
            return true;
        } else throw new CustomerNotFoundException("Customer not found with id " + customerId);
    }
}
