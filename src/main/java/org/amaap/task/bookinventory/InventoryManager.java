package org.amaap.task.bookinventory;

import org.amaap.task.bookinventory.domain.Book;
import org.amaap.task.bookinventory.sales.SalesManager;
import org.amaap.task.bookinventory.writer.InventoryWriter;
import org.amaap.task.bookinventory.writer.ThresholdLimitExceededException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private final InventoryReader inventoryReader;
    private final InventoryWriter inventoryWriter;
    private final SalesManager salesManager;


    public InventoryManager(InventoryReader inventoryReader, InventoryWriter inventoryWriter, SalesManager salesManager) {
        this.inventoryReader = inventoryReader;
        this.inventoryWriter = inventoryWriter;
        this.salesManager = salesManager;
    }


    public List<Book> getInventory() throws SQLException {
        return inventoryReader.getInventory();
    }

    public boolean insertIntoInventory(Book book) throws SQLException, ThresholdLimitExceededException {
        return inventoryWriter.insertBook(book);
    }


    public int updateQuantityFor(int bookId, int quantity) throws SQLException, ThresholdLimitExceededException {
        return inventoryWriter.updateQuantityFor(bookId, quantity);
    }

    public List<Book> filterInventory(String filter) {
        return new ArrayList<Book>();
    }

    public int getTotalAmount(String bookName, int quantity) {

        return salesManager.getTotalAmount(bookName, quantity);
    }
}
