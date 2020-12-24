package com.KozKuzCompany.users.student;

public class BudgetStudent extends Student {

    private double scholarship;

    public BudgetStudent(String name, String groupNumber, int id, int rating, double benefits, double scholarship) {
        super(name, groupNumber, id, rating, benefits);
        this.scholarship = scholarship;
    }

    public double getScholarship() {
        return scholarship;
    }

    public void setScholarship(double scholarship) {
        this.scholarship = scholarship;
    }
}
