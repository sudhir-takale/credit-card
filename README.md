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

#### Assumptions:

    - Credit Card doesn't exist without customer
    - transaction does not exist without credit card

# Solution

# Domain Analysis

### Entity

    - Customer 
        - id
        - name
        - email_address  
    - CreditCard
        - id
        - Customer 
    - Transaction
        - id
        - creditCardId - to refer the credit card with transaction
        - date
        - amount
        - category

### ValueObject

    - Category (enum) - to store the categories like Travel, shopping etc

### Domain Service

    - UnusualSpendAnalyser - it processes ununusual spends on category of personal 

### Controller

    - TransactionController - handle all operations related with transaction like createTransaction, getTransactions and
    - CustomerController -    handle operations related with Customer
    - CreditCard          -   handle creditcard creation, updation etc.
    - ManagerController    -   handle operations related with send alert, trigger analyzer

### Service

    - TransactionService - perform operations related with transaction service  
    - CustomerService     - perform operations related with customer
    - CrediCardService    - peforma operations related with credit card service
    - ManagerService       - used to trigger the domain serice and email send service

### Repositories

    - TransactionRepository - to store the transactions
    - CreditCardRepository - to store the credit card 
    - CustomerRepository - to store the customer data
        