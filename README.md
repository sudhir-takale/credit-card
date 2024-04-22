![image](https://github.com/sudhir-takale/credit-card/assets/93988135/1ec795d9-855c-485b-97d8-b8b58c2ea995)




# Problem Statement

## Unusual Spends

You work at a credit card company and as a value-add they want to start providing alerts to users when their spending in
any particular category is higher than usual.

- Compare the total amount paid for the current month, grouped by category with the previous month
- Filter down to the categories for which the user spent at least 50% more this month than last month
- Compose an e-mail message to the user that lists the categories for which spending was unusually high

Sample Email -

Unusual spending of ₹1076 detected!

Hello Baburao!

We have detected unusually high spending on your card in these categories:

* You spent ₹148 on groceries
* You spent ₹928 on travel

Thanks,

The Credit Card Company

Extensions -

- Change in Email format
- Change in threshold percentage
- Change in usual spending amount calculation logic
- Adding usual spending amount in email

# credit-card

This is credit card task by amaap


class CreditCard {
    public long cardNumber;
    public String cardHolderName;
    private int cvv;
    private Date expiryDate;
    public boolean status;

    CreditCard(cardNumber, holderName, cvv, expiryDate, status)

    Methods:
    - void getDetails()
    - getCreditCard()
    - makeTransaction()
}
class Address {
    String city;
    String district;
    long pinCode;

    Address(city, district, pincode)
}
class ContactDetails {
    long Number;
    String email;

    ContactDetails(Number, email)

    Methods:
    - getContactDetails()
}


class User {
String userName;
Address address;
CreditCard creditCard;
ContactDetails contactDetails;

    User(uName, creditCard, contactDetails, address)

    Methods:
    - getTotalAmount()
    - viewSpending(month)

}


class SpendingManager {
private Map<String, Integer> spendingOfMonth;
User user;
Date date;

    Methods:
    - void getSpendings()
    - addNewSpend(String category, int amount)
    - makeAlert()
    - processSpendingByMonth()

}

class EmailHandler {

    Methods:
    - sendEmail(email address)

}



