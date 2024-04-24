package com.amaap.creditcard.service.communication;

import com.amaap.creditcard.domain.model.entity.Transaction;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailService {


    public static void sendEmail(String subject, String body, String email){
//        if (!isValidEmail(email)) throw new InvalidEmailIdException("Invalid email id:"+email);
//        if (body == null || body.isEmpty()) throw new InvalidEmailBodyException("Email body should have content");
//        if(subject == null || subject.isEmpty()) throw new InvalidEmailSubjectException("Email subject is must");
        String fromEmail = "sudhirtakale99@gmail.com";
        String toEmail = email;
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
