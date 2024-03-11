package org.amaap.task.bookinventory;

import org.amaap.task.bookinventory.database.Database;
import org.amaap.task.bookinventory.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryReader {

    private final Database database;

    public InventoryReader(Database database) {
        this.database = database;

    }


    public List<Book> getInventory() throws SQLException {
        List<Book> allBooks = new ArrayList<>();
        ResultSet rs = database.readAllBooks();
        while (rs.next()) {

            allBooks.add(
                    new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getInt(6), rs.getDouble(7))
            );
        }
        return allBooks;
    }

    public List<Book> filterInventory(String filter) {
        return Collections.emptyList();
    }
}
