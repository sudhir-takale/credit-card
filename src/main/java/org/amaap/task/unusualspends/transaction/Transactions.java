package org.amaap.task.unusualspends.transaction;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Transactions {

    private final List<Transaction> transactionsList = new ArrayList<>();

    public boolean makeTransaction(Transaction transaction) {
        return transactionsList.add(transaction);
    }

    public int getTotalAmountSpent() {
        return transactionsList.stream().mapToInt(Transaction::getAmount).sum();
    }

    public int getTotalAmountSpentOnLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfCurrentMonth = currentDate.withDayOfMonth(1);
        LocalDate firstDayOfLastMonth = firstDayOfCurrentMonth.minusMonths(1);
        LocalDate lastDayOfLastMonth = firstDayOfCurrentMonth.minusDays(1);

        return transactionsList.stream()
                .filter(transaction -> isWithinDateRange(transaction.getDateOfTransaction(), firstDayOfLastMonth, lastDayOfLastMonth))
                .mapToInt(Transaction::getAmount)
                .sum();
    }
    public List<Transaction> filterTransactionByCurrentMonth()
    {
        return transactionsList.stream().filter((x) -> x.getDateOfTransaction().getMonth() == LocalDate.now().getMonth()).collect(Collectors.toList());
    }

    public List<Transaction> filterTransactionByPreviousMonth()
    {
        return transactionsList.stream().filter((x) -> x.getDateOfTransaction().getMonth() == LocalDate.now().minusMonths(1).getMonth()).collect(Collectors.toList());

    }

    public Map<String, Double> groupAndCalculateTotal() {
        return transactionsList.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Map<String, List<Transaction>> groupTransactionsByCategory() {
        return transactionsList.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory));
    }

    public Map<String, List<Transaction>> groupTransactionsByCategoryOfPreviousMonth() {
        LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);

        // Get transactions from the previous month
        List<Transaction> previousMonthTransactions = transactionsList.stream()
                .filter(transaction -> transaction.getDateOfTransaction().isAfter(firstDayOfCurrentMonth.minusMonths(1))
                        && transaction.getDateOfTransaction().isBefore(firstDayOfCurrentMonth))
                .toList();

        Map<String, List<Transaction>> groupedTransactions = new HashMap<>();
        for (Transaction transaction : previousMonthTransactions) {
            String category = transaction.getCategory();
            groupedTransactions.putIfAbsent(category, new ArrayList<>());
            groupedTransactions.get(category).add(transaction);
        }

        return groupedTransactions;
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
