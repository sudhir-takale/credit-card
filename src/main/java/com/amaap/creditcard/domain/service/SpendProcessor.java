package com.amaap.creditcard.domain.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.dto.UnusualSpendDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpendProcessor {


    public UnusualSpendDto processUnusualSpend(List<Transaction> transactionsOfCurrentMonth, List<Transaction> transactionsOfPreviousMonth, int threshold) {

        List<Transaction> unusualSpendTransactions = new ArrayList<>();
        List<Transaction> usualSpendTransactions = new ArrayList<>();

        for (Transaction currentTransaction : transactionsOfCurrentMonth) {
            Category currentCategory = currentTransaction.getCategory();
            double currentAmount = currentTransaction.getAmount();

            Transaction previousTransaction = transactionsOfPreviousMonth.stream().filter(transaction -> transaction.getCategory().equals(currentCategory)).findFirst().orElse(null);

            if (previousTransaction != null) {
                double previousAmount = previousTransaction.getAmount();
                double percentageIncrease = ((currentAmount - previousAmount) / previousAmount) * 100;

                if (percentageIncrease >= threshold) {
                    unusualSpendTransactions.add(currentTransaction);
                } else usualSpendTransactions.add(currentTransaction);
            }
        }

        Map<Category, Double> unusualSpendCategory = unusualSpendTransactions.stream().collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
        Map<Category, Double> usualSpendCategory = usualSpendTransactions.stream().collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
        return getTotalUnusualSpendAmount(unusualSpendCategory, usualSpendCategory);
    }

    private UnusualSpendDto getTotalUnusualSpendAmount(Map<Category, Double> unusualSpendTransactions, Map<Category, Double> usualSpendTransactions) {
        double unusualSpendAmount = unusualSpendTransactions.values().stream().mapToDouble(Double::doubleValue).sum();
        double usualSpendAmount = usualSpendTransactions.values().stream().mapToDouble(Double::doubleValue).sum();
        return new UnusualSpendDto(unusualSpendTransactions, unusualSpendAmount, usualSpendTransactions, usualSpendAmount);
    }

}
