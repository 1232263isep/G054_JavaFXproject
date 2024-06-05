package model;

public class Notification {
    private final String recipientEmail;
    private final String message;

    public Notification(String recipientEmail, String message) {
        this.recipientEmail = recipientEmail;
        this.message = message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getMessage() {
        return message;
    }
}
