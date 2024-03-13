package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;

public class CreditCardManager {


    private final Customer customer;
    private final CreditCard creditCard;

    public CreditCardManager(Customer customer, CreditCard creditCard) {
        this.customer = customer;
        this.creditCard = creditCard;
    }

    public Customer createCustomer(int id, String name, String email) {
        return Customer.createNewCustomer(id, name, email);

    }


    public boolean createNewCreditCard(int cardNumber, Customer newCustomer) {

        return creditCard.createNewCreditCard(cardNumber, newCustomer);

    }
}
