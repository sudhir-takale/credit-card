package com.amaap.creditcard.domain.service.dto;

import com.amaap.creditcard.domain.model.valueobject.Category;

import java.util.Map;

public class UnusualSpendDto {
    private Map<Category, Double> unusualSpendTransactions;
    private Map<Category, Double> usualSpendTransactions;
    private double unusualSpendAmount;
    private double totalSpendAmount;

    public UnusualSpendDto(Map<Category, Double> unusualSpendTransactions, double unusualSpendAmount, Map<Category, Double> spendTransactions, double totalSpendAmount) {
        this.totalSpendAmount = totalSpendAmount;
        this.unusualSpendAmount = unusualSpendAmount;
        this.unusualSpendTransactions = unusualSpendTransactions;
        this.usualSpendTransactions = spendTransactions;
    }

    public double getTotalSpendAmount() {
        return totalSpendAmount;
    }

    public double getUnusualSpendAmount() {
        return unusualSpendAmount;
    }

    public Map<Category, Double> getUnusualSpendTransactions() {
        return unusualSpendTransactions;
    }

    public Map<Category, Double> getUsualSpendTransactions() {
        return usualSpendTransactions;
    }
}
