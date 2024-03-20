package org.amaap.task.creditcard.alerts;

import org.amaap.task.creditcard.domain.Customer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public class EmailHandler {


    public void sendEmailAlert(String subject, String body, String bEmail) {

        if (bEmail.isEmpty()) return;
        if (subject.isEmpty() || body.isEmpty()) return;
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


    }

    public void sendEmailToCustomer(Map<String, Integer> map, Customer customer) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Unusual spending of Rs").append(getTotalAmount(map)).append(" detected!\n\n");
        emailContent.append("Hello ").append(customer.getName()).append("!\n\n");
        emailContent.append("We have detected unusually high spending on your card in these categories:\n\n");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            emailContent.append("* You spent Rs").append(entry.getValue()).append(" on ").append(entry.getKey()).append("\n");
        }
        emailContent.append("\nThanks,\n\nThe Credit Card Company");


        if (validateData("Unusual Spending of this month", emailContent.toString(), customer.getEmailAddress())) {

            sendEmailAlert("Unusual Spending of this month", emailContent.toString(), customer.getEmailAddress());
        }


    }

    private boolean validateData(String subject, String content, String emailAddress) {
        if (subject.isEmpty() || content.isEmpty() || emailAddress.isEmpty())
            throw new IllegalArgumentException("Check your arguments");

        return true;
    }

    public int getTotalAmount(Map<String, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }
}
