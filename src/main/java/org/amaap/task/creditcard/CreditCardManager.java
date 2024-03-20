package org.amaap.task.creditcard;

import org.amaap.task.creditcard.alerts.EmailHandler;
import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.*;
import org.amaap.task.creditcard.spendinganalyzer.UnusualSpendAnalyzer;
import org.amaap.task.creditcard.transactions.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCardManager {


    private final Customer customer;
    private final CreditCard creditCard;
    private final UnusualSpendAnalyzer unusualSpendAnalyzer;
    private final EmailHandler emailHandler;

    public CreditCardManager(Customer customer, CreditCard creditCard, UnusualSpendAnalyzer unusualSpendAnalyzer, EmailHandler emailHandler) {
        this.customer = customer;
        this.creditCard = creditCard;

        this.unusualSpendAnalyzer = unusualSpendAnalyzer;
        this.emailHandler = emailHandler;
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

    public Map<String, Integer> categoriesInWhichSpendingIsUnusual() {
        Map<String, Integer> result = new HashMap<>();
        if (checkForUnusualSpend()) {
            result = unusualSpendAnalyzer.categoriesInWhichSpendingIsUnusual();
        }
        return result;
    }

    public void sendMessageToCustomer(Customer customer) {
        Map<String, Integer> categories = categoriesInWhichSpendingIsUnusual();
        if (!categories.isEmpty()) {
            emailHandler.sendEmailToCustomer(categories,customer);
        }

    }
}
