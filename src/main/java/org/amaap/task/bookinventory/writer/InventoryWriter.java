package org.amaap.task.bookinventory.writer;

import org.amaap.task.bookinventory.database.Database;
import org.amaap.task.bookinventory.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryWriter {

    private Database database;

    public InventoryWriter(Database database) {
        this.database = database;
    }

    public boolean insertBook(Book book) throws SQLException, ThresholdLimitExceededException {

        if (book.getQuantity() > 100) {
            throw new ThresholdLimitExceededException("You can't update quantity above 100");
        }

        return database.insertBook(book) == 1;
    }

    public int updateQuantityFor(int bookId, int quantity) throws SQLException, ThresholdLimitExceededException {

        ResultSet resultSet = database.getBookFor(bookId);
        resultSet.next();
       int existingQuantity =  resultSet.getInt("quantity");
        int newQuantity = existingQuantity + quantity;

        if (newQuantity > 100)
            throw new ThresholdLimitExceededException("You can't update quantity above 100");

        return database.updateQuantityFor(bookId, quantity);
    }

}
