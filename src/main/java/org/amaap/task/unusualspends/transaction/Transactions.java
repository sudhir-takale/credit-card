package org.amaap.task.unusualspends.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transactions {

    private List<Transaction> transactionsList = new ArrayList<>();

    public boolean makeTransaction(Transaction transaction) {
        return transactionsList.add(transaction);

    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public int getTotalAmountSpent() {
        int totalAmountSpent = 0;
        for (Transaction transaction : transactionsList) totalAmountSpent += transaction.getAmount();
        return totalAmountSpent;
    }

    public int getTotalAmountSpentOnLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfCurrentMonth = currentDate.withDayOfMonth(1);
        LocalDate firstDayOfLastMonth = firstDayOfCurrentMonth.minusMonths(1);
        LocalDate lastDayOfLastMonth = firstDayOfCurrentMonth.minusDays(1);

        int totalAmount = 0;

        for (Transaction transaction : transactionsList) {
            LocalDate transactionDate = transaction.getDateOfTransaction();
            if (transactionDate.isAfter(firstDayOfLastMonth) || transactionDate.isEqual(firstDayOfLastMonth)) {
                if (transactionDate.isBefore(lastDayOfLastMonth) || transactionDate.isEqual(lastDayOfLastMonth)) {
                    totalAmount += transaction.getAmount();
                }
            }
        }

        return totalAmount;
    }
}
