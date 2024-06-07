package model.repository;

import model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private final List<Notification> notifications;

    public NotificationRepository() {
        notifications = new ArrayList<>();
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.sendNotification();
    }

    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }
}
