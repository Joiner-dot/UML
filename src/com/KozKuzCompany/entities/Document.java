package com.KozKuzCompany.entities;

import com.KozKuzCompany.interfaces.IPublicatable;

import java.util.ArrayList;
import java.util.List;

public class Document implements IPublicatable {

    int id ;
    String title;
    String description;
    List<Integer> studentsId = new ArrayList<Integer>();
    int studentId;
    boolean confirmed = false;
    String executableDate = "";

    public Document(int id, String title, String description, int studentId) {
        this.title = title;
        this.description = description;
        this.studentId = studentId;
        this.id = id;
    }

    public Document(String title, String description, ArrayList<Integer> studentsId) {
        this.title = title;
        this.description = description;
        this.studentsId = studentsId;
        this.id++;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getStudentsId() {

        return studentsId;
    }

    public void setStudentsId(List<Integer> studentsId) {
        this.studentsId = studentsId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getExecutableDate() {
        return executableDate;
    }

    public void setExecutableDate(String executableDate) {
        this.executableDate = executableDate;
    }
}
