package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Optional;

public class CreditCardService {
    private static final int SAVE_SUCCESS = 1;
    private final CustomerService customerService;
    private final CreditCardRepository creditCardRepository;

    @Inject
    public CreditCardService(CreditCardRepository creditCardRepository, CustomerService customerService) {
        this.creditCardRepository = creditCardRepository;
        this.customerService = customerService;
    }

    public boolean create(int customerId) throws CustomerNotFoundException {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Invalid customerId: " + customerId);
        }

        Optional<Customer> existingCustomer = customerService.getCustomerById(customerId);
        if (existingCustomer.isPresent()) {
            CreditCard card = new CreditCard(existingCustomer.get());
            return creditCardRepository.save(card) == SAVE_SUCCESS;
        } else {
            throw new CustomerNotFoundException("Customer not found with id " + customerId);
        }
    }


    public List<CreditCard> getCreditCards() {
        return creditCardRepository.getCreditCards();
    }
}

