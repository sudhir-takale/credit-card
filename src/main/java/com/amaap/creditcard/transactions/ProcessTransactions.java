package com.amaap.creditcard.transactions;


import com.amaap.creditcard.domain.CreditCard;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class ProcessTransactions {
    private CreditCard creditCard;

    public ProcessTransactions(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Transaction> getAllTransactions() {

        return creditCard.getTransactions();
    }


    public int getTotalAmountSpentOnCurrentMonth() {

        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        LocalDate lastDayOfMonth = firstDayOfMonth.plus(Period.ofMonths(1)).minusDays(1);

        int totalAmount = creditCard.getTransactions().stream()
                .filter(transaction ->
                        !transaction.getDateOfTransaction().isBefore(firstDayOfMonth) &&
                                !transaction.getDateOfTransaction().isAfter(lastDayOfMonth))
                .mapToInt(Transaction::getAmount)
                .sum();

        return totalAmount;
    }

    public int getTotalAmountSpentOnLastMonth() {
        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfLastMonth = currentDate.minus(Period.ofMonths(1)).withDayOfMonth(1);

        LocalDate lastDayOfLastMonth = currentDate.minus(Period.ofMonths(1));

        int totalAmount = creditCard.getTransactions().stream()
                .filter(transaction ->
                        !transaction.getDateOfTransaction().isBefore(firstDayOfLastMonth) &&
                                !transaction.getDateOfTransaction().isAfter(lastDayOfLastMonth))
                .mapToInt(Transaction::getAmount)
                .sum();

        return totalAmount;
    }

    public List<Transaction> getCurrrentMonthTransactions() {
        return creditCard.getTransactions().stream().filter((x) -> x.getDateOfTransaction().getMonth() ==
                LocalDate.now().getMonth()).collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsOfLastMonth() {
        return creditCard.getTransactions().stream().filter((x) -> x.getDateOfTransaction().getMonth()
                        == LocalDate.now().minusMonths(1).getMonth()).
                collect(Collectors.toList());
    }

    public Map<String, Integer> groupTransactionsByCategoryOfCurrentMonth() {
        List<Transaction> currentMonthTransactions = getCurrrentMonthTransactions();

        return currentMonthTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summingInt(Transaction::getAmount)));
    }


    public Map<String, Integer> groupTransactionsByCategoryOfLastMonth() {
        List<Transaction> lastMonthTransactions = getTransactionsOfLastMonth();

        return lastMonthTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summingInt(Transaction::getAmount)));
    }
}

