package org.amaap.task.bookinventory.database;

import org.amaap.task.bookinventory.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;


@Tag("integration")
@ExtendWith(MockitoExtension.class)
public class DatabaseTest {
    private Database database;

    @BeforeEach
    void setup() throws SQLException, ClassNotFoundException {
        database = new MYSQLDatabase("jdbc:mysql://localhost/amaap", "root", "root");

    }

//    @AfterEach
//    void cleanup() throws SQLException, ClassNotFoundException {
//        cleanDatabase("jdbc:mysql://localhost/amaap", "root", "root");
//    }


    @Test
    public void shouldAbleToReadInventory() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = database.readAllBooks();
        Assertions.assertNotNull(resultSet);
    }

    @Test
    public void shouldAbleToAddNewBookIntoInventory() throws SQLException {
        Book book = new Book(3, "Design Pattern", "kevin", "laxmi",
                "Education", 20, 23.0);
        Assertions.assertEquals(1, database.insertBook(book));

    }


    @Test
    void shouldAblToUpdateQuantityOfBookWithGivenQuantity() throws SQLException {

        Book book = new Book(1, "Design Pjjkbattern", "kevin", "laxmi",
                "Education", 10, 23.0);
        database.insertBook(book);

        Assertions.assertEquals(1, database.updateQuantityFor(1, 60));

    }

    @Test
    void shouldReturnBookByBookId() throws SQLException {
        Book book = new Book(1, "Design Pattern", "kevin", "laxmi",
                "Education", 20, 23.0);
        database.insertBook(book);
        ResultSet rs = database.getBookFor(1);
        Assertions.assertNotNull(rs);


    }


}
