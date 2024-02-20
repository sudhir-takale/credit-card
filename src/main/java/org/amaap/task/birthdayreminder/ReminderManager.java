package org.amaap.task.birthdayreminder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ReminderManager {

    List<Friend> friendList = new ArrayList<>();
    List<Friend> remList = new ArrayList<>();

    public void loadData() {
        try {
            Connection con = DatabaseConnection.getDbConnection();
            String selectQuery = "SELECT fname, lname, birthDate, email FROM person";

            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                String email = resultSet.getString("email");
                LocalDate currentDate = LocalDate.now();

                Friend friend = new Friend(fname, lname, birthDate, email);
                if (birthDate.getMonth() == currentDate.getMonth() &&
                        birthDate.getDayOfMonth() == currentDate.getDayOfMonth()) {
                    friendList.add(friend);
                } else {
                    remList.add(friend);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error interacting with the database: " + e.getMessage());
        }

    }

    public String checkBirthday() {
        String remSubject = "Birthday Reminder";
        String greetSubject = "Happy birthday!";

        if (!friendList.isEmpty()) {
            for (Friend friend : friendList) {
                String bEmail = friend.getEmail();
                String fname = friend.getFname();
                String greetMessage = "Happy birthday, dear " + fname + "!";
                sendGreetings(greetSubject, greetMessage, bEmail);
            }
        } else {
            return "No ones having birthday today";
        }

        if (!remList.isEmpty()) {
            StringBuilder fnamesBuilder = new StringBuilder();
            for (Friend friend : friendList) {
                fnamesBuilder.append(friend.getFname()).append(", ");
            }
            String allFirstNames = fnamesBuilder.toString().replaceAll(", $", "");
            String remMessage = "Today is " + allFirstNames + "'s birthday.\n" +
                    "    Don't forget to send them a message!";
            for (Friend friend : remList) {
                String bEmail = friend.getEmail();
                sendGreetings(remSubject, remMessage, bEmail);
            }
        } else {
            return "No ones having birthday today";

        }
        return "No ones having birthday today";


    }


    public String sendGreetings(String subject, String body, String bEmail) {

        if (bEmail.isEmpty()) return "One recipient should be provide";
        if (subject.isEmpty() || body.isEmpty()) return "Please check subject or Body";
        String fromEmail = "sudhirtakale99@gmail.com";
        String toEmail = bEmail;
        String password = "vvye muxc nawq phgy";


        String host = "127.0.0.1";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }


        return "Email Sent Successfully";
    }

    public String addBirthday() throws RuntimeException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name");
        String fname = scanner.next();

        System.out.println("Enter Last Name");
        String lname = scanner.next();

        System.out.println("Enter Birth Date in Form YYYY-MM-DD");
        LocalDate birthDate = LocalDate.parse(scanner.next());

        System.out.println("Enter Your Email");
        String email = scanner.next();

        String insertQuery = "INSERT INTO person (fname, lname, birthDate, email) VALUES ('" + fname + "', '" + lname + "', '" + birthDate + "','" + email + "')";

        try (Connection con = DatabaseConnection.getDbConnection();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(insertQuery);

            return "Record inserted successfully.";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
