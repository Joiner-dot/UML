package com.KozKuzCompany.services.support;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Notification;
import com.KozKuzCompany.users.student.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    public static Notification createNotification(Document document) {

        Notification notification = new Notification(document.getStudentId(),
                "Notification: " + document.getTitle(),
                document.getDescription());
        return notification;
    }


    public static void publishNotification(Notification notification, String state) {
        addNotificationToDB(notification, state);
    }

    public static List<Notification> requestNotificationsFromDB(int studentId) {
        List<Notification> notifications = new ArrayList<>();
        try {
            PreparedStatement statement = DBWorkerService.connection.prepareStatement
                    ("SELECT * FROM notifications WHERE `userid` = ? ");
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"));
                notifications.add(notification);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return notifications;
    }

    private static void addNotificationToDB(Notification notification, String state) {
        PreparedStatement statement = null;
        try {
            statement = DBWorkerService.connection.prepareStatement
                    ("INSERT INTO `UML`.`notifications` (`title`, `description`, `userid`) VALUES (?,?,?);");
            statement.setString(1, notification.getTitle());
            statement.setString(2, notification.getDescription() + " - " + state);
            statement.setInt(3, notification.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
