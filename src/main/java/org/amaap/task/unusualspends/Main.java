package org.amaap.task.unusualspends;

public class Main {


    public static void main(String[] args) {
        CreditCardManager creditCardManager = new CreditCardManager();
        creditCardManager.createCustomer(1, "Sudhir T","sudhirtakale@gmail.com");
        creditCardManager.checkAndSendEmailAlert("user@example.com");
    }


}
