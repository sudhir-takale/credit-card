package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.domain.model.entity.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FakeInMemoryDatabase implements InMemoryDatabase {
    private int customerIdCounter = 0;
    private int creditCardIdCounter = 0;
    private int transactionIdCounter = 0;
    private List<Customer> customerTable = new ArrayList<>();
    private List<CreditCard> creditCardTable = new ArrayList<>();
    private List<Transaction> transactionTable = new ArrayList<>();

    @Override
    public Customer add(Customer customer) {
        customer.setId(++customerIdCounter);
        customerTable.add(customer);
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customerTable;
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {

        return customerTable.stream().filter(customer1 -> customer1.getId() == id).findFirst();
    }

    @Override
    public int insert(CreditCard card) {
        card.setId(++creditCardIdCounter);
        creditCardTable.add(card);
        return 1;
    }

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setId(++transactionIdCounter);
        this.transactionTable.add(transaction);
        return transaction;
    }

}
