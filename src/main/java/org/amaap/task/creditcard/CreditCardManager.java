package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.*;

public class CreditCardManager {


    private final Customer customer;
    private final CreditCard creditCard;

    public CreditCardManager(Customer customer, CreditCard creditCard) {
        this.customer = customer;
        this.creditCard = creditCard;
    }

    public Customer createCustomer(int id, String name, String email) throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        return Customer.createNewCustomer(id, name, email);

    }


    public boolean createNewCreditCard(int cardNumber, Customer newCustomer) throws InvalidCardNumberException, NullCustomerException {

        return creditCard.createNewCreditCard(cardNumber, newCustomer);

    }
}
