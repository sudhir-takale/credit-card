package com.amaap.creditcard.spendinganalyzer;


import com.amaap.creditcard.spendinganalyzer.exceptions.InvalidThresholdPercentageException;
import com.amaap.creditcard.transactions.ProcessTransactions;

import java.util.HashMap;
import java.util.Map;

public class UnusualSpendAnalyzer {

    private final ProcessTransactions processTransactions;
    private double threshold = 150.0;

    public UnusualSpendAnalyzer(ProcessTransactions processTransactions) {
        this.processTransactions = processTransactions;

    }

    public double getThreshold() {
        return threshold;
    }

    public double setThreshold(double threshold) throws InvalidThresholdPercentageException {
        if (threshold <= 0) throw new InvalidThresholdPercentageException("Threshold value can't be Zero");
        return this.threshold = threshold;
    }

    public boolean isThereIsUnusualSpendingThisMonth() {
        int currentMonthSpending = processTransactions.getTotalAmountSpentOnCurrentMonth();
        int lastMonthSpending = processTransactions.getTotalAmountSpentOnLastMonth();

        int thresholdAmount = (int) (lastMonthSpending * (threshold / 100));

        return currentMonthSpending >= thresholdAmount;
    }

    public Map<String, Integer> categoriesInWhichSpendingIsUnusual() {
        Map<String, Integer> currentMonthSpending = processTransactions.groupTransactionsByCategoryOfCurrentMonth();
        Map<String, Integer> lastMonthSpending = processTransactions.groupTransactionsByCategoryOfLastMonth();

        return calculateUnusualMonthSpending(currentMonthSpending, lastMonthSpending);
    }

    private Map<String, Integer> calculateUnusualMonthSpending(Map<String, Integer> currentMonthSpending, Map<String, Integer> lastMonthSpending) {
        Map<String, Integer> unusualCategories = new HashMap<>();

        for (Map.Entry<String, Integer> currentEntry : currentMonthSpending.entrySet()) {
            String category = currentEntry.getKey();
            int currentAmount = currentEntry.getValue();

            if (lastMonthSpending.containsKey(category)) {
                int lastMonthAmount = lastMonthSpending.get(category);

                double thresholdAmount = lastMonthAmount * (threshold / 100.0);
                double increasePercentage = ((double) (currentAmount - lastMonthAmount) / lastMonthAmount) * 100;

                if (increasePercentage > 50.0 && currentAmount >= thresholdAmount) {
                    unusualCategories.put(category, currentAmount);
                }
            }
        }

        return unusualCategories;
    }


}
