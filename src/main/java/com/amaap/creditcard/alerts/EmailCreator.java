package com.amaap.creditcard.alerts;

public class EmailCreator {

    private String subject;
    private String bodyContent;
    private String footer;

    public EmailCreator(String subject, String bodyContent, String footer) {
        validate(subject, bodyContent, footer);

        this.subject = subject;
        this.bodyContent = bodyContent;
        this.footer = footer;

    }

    private static void validate(String subject, String body, String footer) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty");
        }
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Body content cannot be null or empty");
        }
        if (footer == null || footer.isEmpty()) {
            throw new IllegalArgumentException("Footer cannot be null or empty");
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public String getFooter() {
        return footer;
    }

    public EmailCreator createCustomEmail() {

        return new EmailCreator("Sudhir", "How are you ", "The credit card");
    }

}
