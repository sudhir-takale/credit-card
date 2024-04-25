package com.amaap.creditcard;

import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.repository.TransactionRepository;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransactionRepository.class).to(InMemoryTransactionRepository.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class).asEagerSingleton();
    }
}
