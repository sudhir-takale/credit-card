package org.amaap.task.bookinventory.writer;

import org.amaap.task.bookinventory.database.Database;
import org.amaap.task.bookinventory.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryWriterTest {

    @Mock
    Database database;


    @InjectMocks
    InventoryWriter inventoryWriter;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void shouldAbleToAddNewBookToInventory() throws SQLException, ThresholdLimitExceededException {
//       Arrange
        Book book = new Book(1, "Design Pattern", "kevin", "laxmi", "Education", 20, 23.0);
//        Act
        when(database.insertBook(book)).thenReturn(1);
        boolean result = inventoryWriter.insertBook(book);
//        Assert
        Assertions.assertTrue(result);

    }

    @Test
    void shouldNotAbleToAddMoreThanHundredQuantityInInventory() throws SQLException {
        Book book = new Book(1, "Design Pattern", "kevin", "laxmi",
                "Education", 120, 23.0);

        Throwable message = assertThrows(ThresholdLimitExceededException.class, () -> {
            inventoryWriter.insertBook(book);
        });

        Assertions.assertEquals("You can't update quantity above 100", message.getMessage());


    }

    @Test
    void shouldAbleToUpdateTheQuantityOfBookWithId() throws SQLException, ThresholdLimitExceededException {
        ResultSet resultSet = mock(ResultSet.class);

        when(database.getBookFor(3)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("quantity")).thenReturn(20);
        when(database.updateQuantityFor(3, 40)).thenReturn(1);
        System.out.println(resultSet.getInt("quantity"));

        int noOfRowsAffected = inventoryWriter.updateQuantityFor(3, 40);
        Assertions.assertEquals(1, noOfRowsAffected);
    }

    @Test
    void shouldThrowExceptionWhenAboveThreshold() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(database.getBookFor(1)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("quantity")).thenReturn(80);

        assertThrows(ThresholdLimitExceededException.class, () -> {
            inventoryWriter.updateQuantityFor(1, 30);
        });
        Mockito.verify(database, times(1)).getBookFor(1);
        verifyNoMoreInteractions(database);
    }
}


