package com.amaap.creditcard;

import com.amaap.creditcard.repository.CustomerRepository;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.TransactionRepository;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransactionRepository.class).to(InMemoryTransactionRepository.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class).asEagerSingleton();
    }
}
