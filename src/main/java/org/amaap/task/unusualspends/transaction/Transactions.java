package org.amaap.task.unusualspends.transaction;

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


}
