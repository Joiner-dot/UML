package com.KozKuzCompany.services.support;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Notification;
import com.KozKuzCompany.users.student.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorkerService {

    static Connection connection;

    private static String url = "jdbc:mysql://localhost:3306/UML?useUnicode=true&useSSL=true&use" +
            "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static List<Student> selectStudentsFromDB(List<Integer> studentsId) {

        return null;
    }

    public static void addInfoToDB(List<String> data) {
        try {
            if (data.get(0).equals("documents")) {
                PreparedStatement statement = connection.prepareStatement
                        ("INSERT INTO `UML`.`documents` (`title`, `description`, `userid`) VALUES (?,?,?);");
                statement.setString(1, data.get(1));
                statement.setString(2, data.get(2));
                statement.setInt(3, Integer.parseInt(data.get(3)));
                statement.executeUpdate();
            }
            if (data.get(0).equals("transactions")) {
                PreparedStatement statement = connection.prepareStatement
                        ("INSERT INTO `UML`.`transactions` (`amount`, `description`, `userid`, `documented`) " +
                                "VALUES (?,?,?,?);");
                statement.setDouble(1, Double.parseDouble(data.get(2)));
                statement.setString(2, data.get(3));
                statement.setInt(3, Integer.parseInt(data.get(4)));
                statement.setBoolean(4, Boolean.parseBoolean(data.get(5)));
                statement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Success");
    }

    public static void modifyInfoInDB(List<String> data) {
        if (data.get(0).equals("users")) {
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE `UML`.`users` SET `benefits` = ? "
                        + "WHERE (`id` = ?);");
                statement.setDouble(1, Double.parseDouble(data.get(2)));
                statement.setInt(2, Integer.parseInt(data.get(1)));
                statement.executeUpdate();
                statement = connection.prepareStatement("UPDATE `UML`.`documents` SET `confirmed` = ?" +
                        " WHERE (`id` = ?);");
                statement.setInt(1,Integer.parseInt(data.get(4)));
                statement.setInt(2, Integer.parseInt(data.get(3)));
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static List<Document> selectDocuments() {
        List<Document> documents = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM documents WHERE `confirmed` IS NULL ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Document document = new Document(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("userid"));
                documents.add(document);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return documents;
    }

    public static ResultSet logIn(String login, String password) {

        try {
            connection = DriverManager.getConnection(url, "root", "8951fdsa");
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM users WHERE `name` = ? AND `password` = ? ");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
