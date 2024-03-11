package org.amaap.task.bookinventory.database;

import org.amaap.task.bookinventory.domain.Book;

import java.sql.*;

public class MYSQLDatabase implements Database {

    private Connection connection;


    public MYSQLDatabase(String url, String username, String password) throws SQLException, ClassNotFoundException {
        this.connection = getConnection(url, username, password);
    }

    private Connection getConnection(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public ResultSet readAllBooks() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM book_inventory");
    }

    @Override
    public int insertBook(Book book) throws SQLException {
        String query = "INSERT INTO book_inventory (bookId, bookName, author, publisher, category, quantity, price) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, book.getBookId());
            statement.setString(2, book.getBookName());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getCategory());
            statement.setInt(6, book.getQuantity());
            statement.setDouble(7, book.getPrice());

            return statement.executeUpdate();
        }
    }

    @Override
    public int getQuantityFor(int bookId) throws SQLException {
        String query = "SELECT quantity FROM book_inventory WHERE bookId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            } else {
                // Handle the case where the bookId is not found
                throw new SQLException("Book not found for bookId: " + bookId);
            }
        }
    }


    @Override
    public int updateQuantityFor(int bookId, int quantity) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("select * from book_inventory where bookId = ?");
        statement.setInt(1, bookId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        int quantityToUpdate = resultSet.getInt("quantity") + quantity;

        PreparedStatement st = connection.prepareStatement("update book_inventory set quantity = ? where bookId = ?");
        st.setInt(1, quantityToUpdate);
        st.setInt(2, bookId);

        return st.executeUpdate();
    }


    @Override
    public ResultSet getBookFor(int bookId) throws SQLException {
        System.out.println("Get book for method called");
        String query = "select * from book_inventory where bookId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, bookId);
        return statement.executeQuery();

    }


}
