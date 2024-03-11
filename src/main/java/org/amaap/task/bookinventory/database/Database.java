package org.amaap.task.bookinventory.database;

import org.amaap.task.bookinventory.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    ResultSet readAllBooks() throws SQLException;

    int insertBook(Book book) throws SQLException;

    int updateQuantityFor(int bookId, int quantity) throws SQLException;

    int getQuantityFor(int bookId) throws SQLException;
    ResultSet getBookFor(int bookId) throws SQLException;
}
