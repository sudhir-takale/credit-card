package org.amaap.task.bookinventory;

import org.amaap.task.bookinventory.domain.Book;
import org.amaap.task.bookinventory.sales.SalesManager;
import org.amaap.task.bookinventory.writer.InventoryWriter;
import org.amaap.task.bookinventory.writer.ThresholdLimitExceededException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryManagerTest {

    @Mock
    InventoryReader inventoryReader;
    @Mock
    InventoryWriter inventoryWriter;
    @Mock
    SalesManager salesManager;
    @InjectMocks
    InventoryManager inventoryManager;


    @Test
    void inventoryShouldBeEmptyOnceInitialized() throws SQLException {
        when(inventoryReader.getInventory()).thenReturn(Collections.emptyList());
        assertTrue(inventoryManager.getInventory().isEmpty());
    }

    @Test
    void shouldAbleToInsertIntoInventory() throws SQLException, ThresholdLimitExceededException {
        Book book = new Book(1, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
        when(inventoryWriter.insertBook(book)).thenReturn(true);
        boolean result = inventoryManager.insertIntoInventory(book);
        Assertions.assertTrue(result);

    }

    @Test
    void shouldAbleToUpdateQuantityOfBook() throws ThresholdLimitExceededException, SQLException {

        Book book = new Book(1, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
        when(inventoryWriter.updateQuantityFor(1, 20)).thenReturn(1);
        int result = inventoryManager.updateQuantityFor(1, 20);
        assertEquals(1, result);

    }

//    @Test
//    void shouldAbleToSellBookIfQuantityOfBookIsAvailable() throws ThresholdLimitExceededException, SQLException {
//        Book book = new Book(1, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
//        Book book2 = new Book(2, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
//        Book book3 = new Book(3, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
//        inventoryManager.insertIntoInventory(book);
//        inventoryManager.insertIntoInventory(book2);
//        inventoryManager.insertIntoInventory(book3);
//        when(salesManager.getTotalAmount("design pattern", 2)).thenReturn(34);
//        assertEquals(34, inventoryManager.getTotalAmount("Design Pattern", 2));
//
//
//    }


}
