package com.amaap.creditcard.alerts;

import com.amaap.creditcard.domain.Customer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

import static com.amaap.creditcard.alerts.EmailValidator.validate;

public class EmailHandler {


    public void sendEmailAlert(String subject, String body, String recipientEmail) {

      EmailValidator.validateEmail(subject, body, recipientEmail);

        String fromEmail = "sudhirtakale99@gmail.com";
        String password = "vvye muxc nawq phgy";

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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }


    }

    public void sendEmailToCustomer(Map<String, Integer> map, Customer customer) {

        EmailCreator emailCreator = createCustomEmail();
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("Unusual spending of Rs").append(getTotalAmount(map)).append(" detected!\n\n");
        emailContent.append("Hello ").append(customer.getName()).append("!\n\n");
        emailContent.append("Hello ").append(customer.getName()).append("!\n\n");

        String body = "We have detected unusually high spending on your card in these categories:";
        if (!emailCreator.getBodyContent().isEmpty()) body = emailCreator.getBodyContent();
        emailContent.append(body).append("\n\n");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            emailContent.append("* You spent Rs").append(entry.getValue()).append(" on ").append(entry.getKey()).append("\n");
        }
        String footer = "\nThanks,\n\nThe Credit Card Company";
        if (!emailCreator.getFooter().isEmpty()) {
            footer = emailCreator.getFooter();
        }

        emailContent.append("\n Thanks,\n\n").append(footer);
        String subject = "Unusual Spending of this month";

        if (!emailCreator.getSubject().isEmpty()) {
            subject = emailCreator.getSubject();
        }
        if (validate(subject, emailContent.toString(), customer.getEmailAddress())) {

            sendEmailAlert(subject, emailContent.toString(), customer.getEmailAddress());
        }
    }


    public int getTotalAmount(Map<String, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    private EmailCreator createCustomEmail() {
        return new EmailCreator("Custom subject is passed", "Custom Body is passed", "Custom Footer is passed");
    }
}
