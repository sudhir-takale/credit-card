package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.repository.TransactionRepository;
import com.amaap.creditcard.service.exception.TransactionNotFoundException;
import com.google.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Inject
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(int cardId, LocalDate date, Category category, double amount) throws InvalidTransactionParameters {

        Transaction transaction = Transaction.create(cardId, date, category, amount);
        return transactionRepository.save(transaction);

    }

    public List<Transaction> getTransactions() {
        return transactionRepository.getTransactions();
    }

    public Transaction getTransactionFor(int transactionId) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionRepository.getTransactionFor(transactionId);
        if (transaction.isPresent()) return transaction.get();
        else throw new TransactionNotFoundException("Transaction not found with id " + transactionId);
    }

    public Optional<List<Transaction>> getTransactionsOf(int cardId, int month, int year) {
        List<Transaction> transactions = getTransactions();

        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getCardId() == cardId && transaction.getDate().getMonthValue() == month && transaction.getDate().getYear() == year)
                .sorted(Comparator
                .comparing(Transaction::getDate))
                .toList();

        return Optional.of(filteredTransactions.isEmpty() ? new ArrayList<>() : filteredTransactions);
    }

}
