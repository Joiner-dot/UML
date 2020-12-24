package com.KozKuzCompany.services.finance;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.entities.Notification;
import com.KozKuzCompany.entities.Transaction;
import com.KozKuzCompany.services.finance.FinanceManagementService;
import com.KozKuzCompany.services.support.DBWorkerService;
import com.KozKuzCompany.services.support.NotificationService;
import com.KozKuzCompany.users.student.Student;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class BenefitsService extends FinanceManagementService {

    public static double calculatePaymentBenefits(int studentId) {

        return 21000;
    }

    public static void transferPaymentBenefitsToStudent(Document document) {

        Transaction transaction = confirmTransaction(document);
        List<String> data = new ArrayList<>();
        List<String> transactions = new ArrayList<>();

        transactions.add("transactions");
        transactions.add(String.valueOf(document.getId()));
        transactions.add(String.valueOf(calculatePaymentBenefits(document.getStudentId())));
        transactions.add(transaction.getDescription());
        transactions.add(String.valueOf(document.getStudentId()));
        transactions.add("true");
        DBWorkerService.addInfoToDB(transactions);
        Notification notification = NotificationService.createNotification(document);
        NotificationService.publishNotification(notification, "approved");

        data.add("users");
        data.add(String.valueOf(document.getStudentId()));
        data.add(String.valueOf(calculatePaymentBenefits(document.getStudentId())));
        data.add(String.valueOf(document.getId()));
        data.add(String.valueOf(1));
        DBWorkerService.modifyInfoInDB(data);
    }

}
