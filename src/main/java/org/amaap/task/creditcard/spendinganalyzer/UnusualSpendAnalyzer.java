package org.amaap.task.creditcard.spendinganalyzer;

import org.amaap.task.creditcard.transactions.ProcessTransactions;

import java.util.HashMap;
import java.util.Map;

public class UnusualSpendAnalyzer {
    private ProcessTransactions processTransactions;

    public UnusualSpendAnalyzer(ProcessTransactions processTransactions) {
        this.processTransactions = processTransactions;

    }

    public boolean isThereIsUnusualSpendingThisMonth() {

        int currentMonthSpending = processTransactions.getTotalAmountSpentOnCurrentMonth();
        int lastMonthSpending = processTransactions.getTotalAmountSpentOnLastMonth();
        int spending = (lastMonthSpending / 2) * 3;
        System.out.println(currentMonthSpending);
        System.out.println(lastMonthSpending);
        System.out.println(spending);
        return currentMonthSpending >= spending;

    }

    public Map<String, Integer> categoriesInWhichSpendingIsUnusual() {
        Map<String, Integer> currentMonthSpending = processTransactions.groupTransactionsByCategoryOfCurrentMonth();
        Map<String, Integer> lastMonthSpending = processTransactions.groupTransactionsByCategoryOfLastMonth();

        Map<String, Integer> unusualMonthSpending = calculateUnusualMonthSpending(currentMonthSpending, lastMonthSpending);
        return unusualMonthSpending;
    }

    private Map<String, Integer> calculateUnusualMonthSpending(Map<String, Integer> currentMonthSpending, Map<String, Integer> lastMonthSpending) {
        Map<String, Integer> unusualCategories = new HashMap<>();

        for (Map.Entry<String, Integer> currentEntry : currentMonthSpending.entrySet()) {
            String category = currentEntry.getKey();
            int currentAmount = currentEntry.getValue();

            if (lastMonthSpending.containsKey(category)) {
                int lastMonthAmount = lastMonthSpending.get(category);

                double increasePercentage = ((double) (currentAmount - lastMonthAmount) / lastMonthAmount) * 100;

                if (increasePercentage > 50.0) {
                    unusualCategories.put(category, currentAmount);
                }
            }
        }

        return unusualCategories;
    }


}
