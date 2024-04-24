package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.SpendProcessor;
import com.amaap.creditcard.domain.service.dto.UnusualSpendDto;
import com.amaap.creditcard.service.communication.EmailService;
import com.google.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class CreditCardManagementService {
    private final SpendProcessor spendProcessor;
    private final CreditCardService creditCardService;
    private final TransactionService transactionService;

    @Inject
    public CreditCardManagementService(CreditCardService creditCardService, SpendProcessor spendProcessor, TransactionService transactionService) {
        this.creditCardService = creditCardService;
        this.spendProcessor = spendProcessor;
        this.transactionService = transactionService;
    }

    public boolean checkForUnusualSpend() {
        List<CreditCard> creditCards = creditCardService.getCreditCards();
        for (CreditCard creditCard : creditCards) {

            List<Transaction> transactionsOfPreviousMonth = transactionService.getTransactionsOf(creditCard.getCustomer().getId(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getYear()).get();
            List<Transaction> transactionsOfCurrentMonth = transactionService.getTransactionsOf(creditCard.getCustomer().getId(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()).get();

            UnusualSpendDto spendDto = spendProcessor.processUnusualSpend(transactionsOfCurrentMonth, transactionsOfPreviousMonth, 50);

            if (spendDto != null) {

                extractSpend(creditCard.getCustomer().getEmailAddress(), creditCard.getCustomer().getName(), spendDto);
            }

        }
        return true;
    }

    private void extractSpend(String emailAddress, String name, UnusualSpendDto spendDto) {
        Map<Category, Double> spendRecordDto = spendDto.getUnusualSpendTransactions();
        Double unusualAmountSpend = spendDto.getUnusualSpendAmount();
        Double usualSpendAmount = spendDto.getTotalSpendAmount();

        StringBuilder body = new StringBuilder(" \nHello " + name + "!\nWe have detected unusually high spending on your card in these categories:\n");

        for (Map.Entry<Category, Double> entry : spendRecordDto.entrySet()) {
            Category category = entry.getKey();
            double amount = entry.getValue();
            String formattedAmount = "Rs" + String.format("%.2f", amount);
            body.append("* You spent ").append(formattedAmount).append(" on ").append(category).append("\n");
        }

        body.append("\n");
        body.append("Total Unusual spending of ").append("Rs").append(unusualAmountSpend).append(" detected!\n");
        body.append("Your usual spending of last month was Rs").append(usualSpendAmount).append("\n");
        body.append("\nThanks,\nThe Credit Card Company\n");

        String messageBody = body.toString();
        EmailService.sendEmail("Your spend is", messageBody, "sudhirtakale99@gmail.com");
    }

}

