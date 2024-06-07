package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Notification {
    private String recipient;
    private String message;
    private Date date;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.date = new Date();
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void sendNotification() {
        // Define the file name, it can be customized as needed
        String fileName = "notification_" + recipient + "_" + date.getTime() + ".txt";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("To: " + recipient);
            out.println("Date: " + date.toString());
            out.println();
            out.println(message);
        } catch (IOException e) {
            System.err.println("Error writing notification to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
