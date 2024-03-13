package org.amaap.task.unusualspends;

import org.amaap.task.unusualspends.transaction.Transaction;
import org.amaap.task.unusualspends.transaction.Transactions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnusualSpendsAnalyzer {
    Transactions transactions = new Transactions();

    public boolean isSpendingHigherThanUsual() {
        int totalAmountSpentThisMonth = transactions.getTotalAmountSpent();
        int totalAmountSpentLastMonth = transactions.getTotalAmountSpentOnLastMonth();

        double threshold = 1.5;

        return totalAmountSpentThisMonth >= threshold * totalAmountSpentLastMonth;
    }

    public String categoriesInWhichSpentWasUnusual() {
        Map<String, Double> currentMonthSpending = transactions.groupAndCalculateTotal();
        Map<String, Double> previousMonthSpending = transactions.groupTransactionsByCategoryOfPreviousMonth().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        List<String> unusualCategories = currentMonthSpending.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1.5 * previousMonthSpending.getOrDefault(entry.getKey(), 0.0))
                .map(entry -> String.format("* You spent ₹%.2f on %s", entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        if (!unusualCategories.isEmpty()) {
            double totalUnusualSpending = unusualCategories.stream()
                    .mapToDouble(category -> Double.parseDouble(category.split(" ")[5]))
                    .sum();

            return String.format("Unusual spending of %.2f detected!\n" +
                            "Hello User!\n\n" +
                            "We have detected unusually high spending on your card in these categories:\n%s\n\nThanks,\n\nThe Credit Card Company",
                    totalUnusualSpending, String.join("\n", unusualCategories));

        }

        return "Not Found";
    }
}
