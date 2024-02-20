package org.amaap.task.birthdayreminder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;


class ReminderManagerTest {

    ReminderManager reminderManager = new ReminderManager();


    @Test
    void shouldCheckWhetherGotConnectionObjectOrNot() throws SQLException {

        Connection con = null;
        con = DatabaseConnection.getDbConnection();
        assertNotNull(con);

    }

    @Test
    void shouldReturnTrueWhenSuccessfullySendingEmail() throws SQLException {
        String result = reminderManager.sendGreetings("Hello", "How are you today", "shtakale1111@gmail.com");
        String expected = "Email Sent Successfully";
        Assertions.assertEquals(expected, result);
    }


    @Test
    void shouldNotSendGreetingsWithNullSubjectOrBody() {
        // Mocking the sendGreetings method with null subject or body
        String subject = "";
        String body = "";
        String Email = "sudhirtakale99@gmail.com";


        String result = reminderManager.sendGreetings(subject, "how", Email);
        assertEquals("Please check subject or Body", result);

        String newResult = reminderManager.sendGreetings("subject", body, Email);
        assertEquals("Please check subject or Body", newResult);
    }


    @Test
    void shouldReturnFalseIfNoOnehavingBirthDayToday() {
        LocalDate d = LocalDate.now();
        assertEquals("No ones having birthday today", reminderManager.checkBirthday());
    }

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

    @Test
    void shouldReturnErrorIfRecipientNotProvided() {

        String result = reminderManager.sendGreetings("hii", "Hello", " ");
        assertEquals("One recipient should be provide", result);

    }


}