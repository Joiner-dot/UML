package com.KozKuzCompany.users.admin;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Notification;
import com.KozKuzCompany.interfaces.IUser;
import com.KozKuzCompany.services.finance.BenefitsService;
import com.KozKuzCompany.services.support.DBWorkerService;
import com.KozKuzCompany.services.support.NotificationService;
import com.KozKuzCompany.users.student.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.KozKuzCompany.services.finance.BenefitsService.calculatePaymentBenefits;

public class Admin implements IUser {
    List<Document> documents;
    int id;
    String name;

    public Admin(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Student> requestStudents(List<Integer> studentsId) {

        return null;
    }

    public Document createOrder() {

        return null;
    }

    public void executeOrder() {
        Scanner scanner = new Scanner(System.in);
        String command;
        documents = DBWorkerService.selectDocuments();
        for (Document document : documents) {
            System.out.println("Title: " + document.getTitle());
            System.out.println("Description: " + document.getDescription());
            System.out.println("Student's id: " + document.getStudentId());
            System.out.println("Yes/No");
            command = scanner.next();
            if (command.equals("Yes")) {
                Document order = createOrder(document);
                if (order.getTitle().contains("B")) {
                    BenefitsService.transferPaymentBenefitsToStudent(document);
                }
            } else if (command.equals("No")) {
                Notification notification = NotificationService.createNotification(document);
                NotificationService.publishNotification(notification, "rejected");
                List<String> data = new ArrayList<>();
                data.add("users");
                data.add(String.valueOf(document.getStudentId()));
                data.add(String.valueOf(calculatePaymentBenefits(document.getStudentId())));
                data.add(String.valueOf(document.getId()));
                data.add(String.valueOf(0));
                DBWorkerService.modifyInfoInDB(data);
            }
        }
        documents = new ArrayList<>();
    }

    private Document createOrder(Document document) {

        Document order = new Document(document.getId(), document.getTitle() + "O",
                document.getDescription() + " approved", document.getStudentId());
        order.setConfirmed(true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        order.setExecutableDate(dtf.format(now));
        return order;
    }

    public void createNotification() {

    }

    public void receiveDocuments() {

        documents = DBWorkerService.selectDocuments();
        for (Document document : documents) {
            System.out.println("Title: " + document.getTitle());
            System.out.println("Description: " + document.getDescription());
            System.out.println("Student's id: " + document.getStudentId());
            System.out.println();
        }
    }
}
