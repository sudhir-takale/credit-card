package org.amaap.task.birthdayreminder;

import static org.amaap.task.birthdayreminder.ReminderManager.*;

public class BirthdayReminderMain {
    public static void main(String[] args) {

        ReminderManager reminderManager = new ReminderManager();

        reminderManager.addBirthday();
        reminderManager.loadData();
        reminderManager.checkBirthday();
    }
}
