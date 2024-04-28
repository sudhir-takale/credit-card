package com.amaap.creditcard;

import com.amaap.creditcard.controller.CreditCardController;
import com.amaap.creditcard.controller.SpendController;
import com.amaap.creditcard.controller.CustomerController;
import com.amaap.creditcard.controller.TransactionController;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.service.exception.InvalidEmailArgumentException;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws InvalidEmailArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Injector injector = Guice.createInjector(new AppModule());
        TransactionController transactionController = injector.getInstance(TransactionController.class);
        CreditCardController creditCardController = injector.getInstance(CreditCardController.class);
        CustomerController customerController = injector.getInstance(CustomerController.class);
        SpendController spendController = injector.getInstance(SpendController.class);

        System.out.println(customerController.create("Sudhir Takale", "sudhirtakale99@gmail.com"));
        System.out.println(customerController.create("Shaurya Mali", "shtakale1111@gmail.com"));

        System.out.println("Credit card : " + creditCardController.create(1));

        transactionController.createTransaction(1, LocalDate.parse("12-03-2024", formatter), Category.TRAVEL, 23.9);
        transactionController.createTransaction(1, LocalDate.parse("12-03-2024", formatter), Category.TRAVEL, 21.9);
        transactionController.createTransaction(1, LocalDate.parse("12-03-2024", formatter), Category.SHOPPING, 29.9);
        transactionController.createTransaction(1, LocalDate.parse("12-03-2024", formatter), Category.SHOPPING, 80.9);

        transactionController.createTransaction(1, LocalDate.now(), Category.TRAVEL, 134.2);
        transactionController.createTransaction(1, LocalDate.now(), Category.TRAVEL, 534.2);
        transactionController.createTransaction(1, LocalDate.now(), Category.SHOPPING, 934.2);
        transactionController.createTransaction(1, LocalDate.now(), Category.SHOPPING, 234.2);

        System.out.println(spendController.checkForUnusualSpend());

    }
}
