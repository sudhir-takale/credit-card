package com.amaap.creditcard;

import com.amaap.creditcard.controller.CreditCardController;
import com.amaap.creditcard.controller.CustomerController;
import com.amaap.creditcard.controller.TransactionController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        TransactionController controller = injector.getInstance(TransactionController.class);
        CreditCardController creditCardController = injector.getInstance(CreditCardController.class);
        CustomerController customerController = injector.getInstance(CustomerController.class);





    }
}
