package com.amaap.creditcard.domain.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.dto.UnusualSpendDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SpendProcessor {


    public UnusualSpendDto processUnusualSpend(List<Transaction> transactionsOfCurrentMonth, List<Transaction> transactionsOfPreviousMonth, int threshold) {

        Map<Category, Double> unusualSpentCategories = new HashMap<>();
        Map<Category, Double> usualSpentCategories = new HashMap<>();

        Map<Category, Double> currentMonthTransactions = transactionsOfCurrentMonth.stream().collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
        Map<Category, Double> lastMonthTransactions = transactionsOfPreviousMonth.stream().collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));


        for (Map.Entry<Category, Double> currentEntry : currentMonthTransactions.entrySet()) {
            Category category = currentEntry.getKey();
            Double currentAmount = currentEntry.getValue();

            if (lastMonthTransactions.containsKey(category)) {
                Double lastMonthAmount = lastMonthTransactions.get(category);

                double increasePercentage = ((currentAmount - lastMonthAmount) / lastMonthAmount) * 100;

                if (Math.abs(increasePercentage) > threshold) {
                    unusualSpentCategories.put(category, currentAmount);
                } else {
                    usualSpentCategories.put(category, currentAmount);
                }
            } else {
                unusualSpentCategories.put(category, currentAmount);
            }
        }

        return getTotalUnusualSpendAmount(unusualSpentCategories, usualSpentCategories);
    }

    private UnusualSpendDto getTotalUnusualSpendAmount(Map<Category, Double> unusualSpentCategories, Map<Category, Double> usualSpentCategories) {
        double unusualSpendAmount = unusualSpentCategories.values().stream().mapToDouble(Double::doubleValue).sum();
        double usualSpendAmount = usualSpentCategories.values().stream().mapToDouble(Double::doubleValue).sum();
        return new UnusualSpendDto(unusualSpentCategories, unusualSpendAmount, usualSpentCategories, usualSpendAmount);
    }

}
