package com.KozKuzCompany.users.student;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Notification;
import com.KozKuzCompany.interfaces.IUser;
import com.KozKuzCompany.services.support.DocumentService;
import com.KozKuzCompany.services.support.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Student implements IUser {
    String name;
    String groupNumber;
    int id;
    int rating;
    double benefits;
    boolean deducted;
    List<Notification> notifications = new ArrayList<>();

    Student(String name, String groupNumber, int id, int rating, double benefits) {

        this.name = name;
        this.groupNumber = groupNumber;
        this.id = id;
        this.rating = rating;
        this.benefits = benefits;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    private void transferToGroup() {

    }

    private void dropOutOfTheUniversityForFreeWill() {

    }

    private void changeStudyProgram() {

    }

    public void requestPaymentBenefits() {

        System.out.println("Please, fill document: ");
        Document document;
        while (true) {
            document = fillDocument("B300");
            if (DocumentService.validateDocument(document)) {
                break;
            }
        }
        publishToSystem(document);
    }

    private Document fillDocument(String title) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Fill description, please: ");
        String description = scanner.nextLine();
        Document document = new Document(0, title, description, this.id);
        return document;
    }

    private void publishToSystem(Document document) {

        DocumentService.publishDocument(document);

    }

    public void receiveNotifications(int userid) {
        notifications = NotificationService.requestNotificationsFromDB(userid);
        for (Notification notification : notifications) {
            System.out.println(notification.getTitle());
            System.out.println("Description: " + notification.getDescription());
            System.out.println();
        }
    }

    public boolean isDeducted() {
        return deducted;
    }

    public void setDeducted(boolean deducted) {
        this.deducted = deducted;
    }
}
