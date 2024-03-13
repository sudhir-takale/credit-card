package org.amaap.task.unusualspends;

import org.amaap.task.unusualspends.domain.Customer;

public class CreditCardManager {
    UnusualSpendsAnalyzer unusualSpendsAnalyzer = new UnusualSpendsAnalyzer();
    Customer customer = new Customer();

    public UnusualSpendsAnalyzer getUnusualSpendsAnalyzer() {
        return unusualSpendsAnalyzer;
    }

    public Customer createCustomer(int customerId, String customerName, String email) {
        return customer.createCustomer(customerId, customerName, email);


    }




}
