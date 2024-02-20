package org.amaap.task.birthdayreminder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class ReminderManagerTest {

    ReminderManager reminderManager = new ReminderManager();

    @Test
    void shouldNotLoadDataWithInvalidDatabaseConnection() {
        reminderManager.friendList.clear();
        System.setProperty("database.url", "invalid-url");
        reminderManager.loadData();
        assertTrue(reminderManager.friendList.isEmpty());
        System.clearProperty("database.url");
    }

    @Test
    void shouldLoadDataWhoHasBirthdayTodayToFriendList() {
        reminderManager.loadData();
        boolean result = reminderManager.friendList.isEmpty();
        assertEquals(true, result);

    }

    @Test
    void shouldAddANewFriend() {

        String inputData = "shiva\nmane\n2024-05-20\nshiva.mane@gmail.com\n";
        InputStream in = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(in);
        String result = reminderManager.addBirthday();
        assertEquals("Record inserted successfully.", result);
        System.setIn(System.in);

    }


}