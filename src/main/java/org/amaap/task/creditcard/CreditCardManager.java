package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.*;
import org.amaap.task.creditcard.spendinganalyzer.UnusualSpendAnalyzer;
import org.amaap.task.creditcard.transactions.Transaction;

import java.util.List;

public class CreditCardManager {


    private final Customer customer;
    private final CreditCard creditCard;
    private final UnusualSpendAnalyzer unusualSpendAnalyzer;

    public CreditCardManager(Customer customer, CreditCard creditCard, UnusualSpendAnalyzer unusualSpendAnalyzer) {
        this.customer = customer;
        this.creditCard = creditCard;
        this.unusualSpendAnalyzer = unusualSpendAnalyzer;
    }

    public Customer createCustomer(int id, String name, String email) throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        return Customer.createNewCustomer(id, name, email);

    }


    public boolean createNewCreditCard(int cardNumber, Customer newCustomer) throws InvalidCardNumberException, NullCustomerException {

        return creditCard.createNewCreditCard(cardNumber, newCustomer);

    }


    public List<Transaction> getAllTransactions() {
        return Customer.transactions;
    }

    public boolean checkForUnusualSpend() {
        return unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth();
    }
}
