package org.amaap.task.bookinventory;

import org.amaap.task.bookinventory.database.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryReaderTest {

    @Mock
    Database database;

    @InjectMocks
    InventoryReader inventoryReader;



    @Test
    void shouldReturnInventory() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(database.readAllBooks()).thenReturn(resultSet);
        Assertions.assertNotNull(inventoryReader.getInventory());

    }

}
