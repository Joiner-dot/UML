package com.KozKuzCompany.services.support;

import com.KozKuzCompany.entities.Document;
import com.KozKuzCompany.users.admin.Admin;

import java.util.ArrayList;
import java.util.List;

public class DocumentService {

    public static boolean validateDocument(Document document) {

        return !document.getDescription().equals("") && !document.getTitle().equals("");
    }

    public static void publishDocument(Document document) {
        List<String> data = new ArrayList<>();
        data.add("documents");
        data.add(document.getTitle());
        data.add(document.getDescription());
        data.add(String.valueOf(document.getStudentId()));
        DBWorkerService.addInfoToDB(data);
    }
}
