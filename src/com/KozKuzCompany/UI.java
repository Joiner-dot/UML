package com.KozKuzCompany;

import com.KozKuzCompany.services.support.DBWorkerService;
import com.KozKuzCompany.users.admin.Admin;
import com.KozKuzCompany.users.student.BudgetStudent;
import com.KozKuzCompany.users.student.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UI {

    public static void engage() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        int command;
        ResultSet result;
        while (true) {
            System.out.println("Enter login: ");
            login = scanner.next();
            System.out.println("Enter password: ");
            password = scanner.next();
            result = DBWorkerService.logIn(login, password);
            assert result != null;
            if (!result.next()) {
                System.out.println("Incorrect login and password..");
                System.out.println("Try again");
            } else if (!result.getBoolean("isAdmin")) {
                System.out.println("Choose one of this actions: ");
                System.out.println("1 - Change the group");
                System.out.println("2 - Drop out of the university for free will");
                System.out.println("3 - Change study program");
                System.out.println("4 - Request payment benefits");
                System.out.println("5 - Receive notifications");
                if (!result.getBoolean("isbudget")) {
                    System.out.println("6 - Pay tuition payment");
                    System.out.println("7 - Get Payment arrears");
                    System.out.println("0 - Log out");
                } else {
                    System.out.println("6 - Get scholarship");
                    System.out.println("0 - Log out");
                }
            } else if (result.getBoolean("isAdmin")) {
                System.out.println("1 - Request students");
                System.out.println("2 - Execute order");
                System.out.println("3 - Create notification");
                System.out.println("4 - Receive documents");
                System.out.println("0 - Log out");
            }
            result.previous();
            if (result.next()) {
                while (true) {
                    command = scanner.nextInt();
                    if (!result.getBoolean("isAdmin")) {
                        Student student = new BudgetStudent(result.getString("name"),
                                result.getString("groupNumber"),
                                result.getInt("id"),
                                result.getInt("rating"),
                                result.getInt("benefits"),
                                result.getInt("scholarship"));

                        if (command == 4) {
                            System.out.println("You selected request payment benefits");
                            student.requestPaymentBenefits();
                        }

                        if (command == 5) {
                            System.out.println("You selected receive notifications");
                            student.receiveNotifications(student.getId());
                        }
                    }

                    if (result.getBoolean("isAdmin")) {
                        Admin admin = new Admin(result.getInt("id"), result.getString("name"));
                        if (command == 2) {
                            admin.executeOrder();
                        }

                        if (command == 4) {
                            admin.receiveDocuments();
                        }
                    }
                    if (command == 0) {
                        break;
                    }
                }
            }
        }
    }
}
