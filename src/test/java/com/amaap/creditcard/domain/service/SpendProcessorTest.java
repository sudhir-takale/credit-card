package com.amaap.creditcard.domain.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.dto.UnusualSpendDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.amaap.creditcard.domain.model.entity.Transaction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class SpendProcessorTest {


    @Test
    void shouldBeAbleToProcessTransactions() throws InvalidTransactionParameters {
        // arrange
        List<Transaction> currentMonthTransactions = List.of(create(1, LocalDate.now(), Category.TRAVEL, 12.34), create(1, LocalDate.now(), Category.SHOPPING, 10.34), create(1, LocalDate.now(), Category.MEDICINE, 10.34));
        List<Transaction> lastMonthTransactions = List.of(create(1, LocalDate.now(), Category.TRAVEL, 12.34), create(1, LocalDate.parse("10-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), Category.TRAVEL, 45.5), create(1, LocalDate.parse("16-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")), Category.TRAVEL, 15.5));

        //act
        UnusualSpendDto unusualSpendDto = new SpendProcessor().processUnusualSpend(currentMonthTransactions, lastMonthTransactions, 20);

        // assert
        assertNotNull(unusualSpendDto);
        assertEquals(3, unusualSpendDto.getUnusualSpendTransactions().size());
        assertEquals(33.02, unusualSpendDto.getUnusualSpendAmount(), 0.01);

    }

    @Test
    void shouldReturnZeroSizeSpendTransactionsIfNoTransactionHasUnusualSpend() throws InvalidTransactionParameters {
        // arrange
        List<Transaction> currentMonthTransactions = List.of(create(1, LocalDate.now(), Category.TRAVEL, 2.34));
        List<Transaction> lastMonthTransactions = List.of(create(1, LocalDate.now(), Category.TRAVEL, 2.34));

        // act
        UnusualSpendDto unusualSpendDto = new SpendProcessor().processUnusualSpend(currentMonthTransactions, lastMonthTransactions, 20);

        // assert
        assertNotNull(unusualSpendDto);
        assertEquals(0, unusualSpendDto.getUnusualSpendTransactions().size());
        System.out.println(unusualSpendDto.getUnusualSpendAmount());
        assertEquals(0.0, unusualSpendDto.getUnusualSpendAmount(), 0.01);

    }

}