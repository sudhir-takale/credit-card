package com.amaap.creditcard;

import com.amaap.creditcard.alerts.EmailHandler;
import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.*;
import com.amaap.creditcard.spendinganalyzer.UnusualSpendAnalyzer;
import com.amaap.creditcard.spendinganalyzer.exceptions.InvalidThresholdPercentageException;

import java.util.HashMap;
import java.util.Map;

public class CreditCardManager {


    private final CreditCard creditCard;
    private final UnusualSpendAnalyzer unusualSpendAnalyzer;
    private final EmailHandler emailHandler;

    public CreditCardManager( CreditCard creditCard, UnusualSpendAnalyzer unusualSpendAnalyzer, EmailHandler emailHandler) {
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
            emailHandler.sendEmailToCustomer(categories, customer);
        }

    }

    public double setThresholdPercent(int thresholdPercent) throws InvalidThresholdPercentageException {
        return unusualSpendAnalyzer.setThreshold(thresholdPercent);

    }
}
