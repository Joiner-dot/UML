package com.KozKuzCompany.entities;

import com.KozKuzCompany.interfaces.IPublicatable;

public class Transaction implements IPublicatable {

    int id;
    double amount;
    String description;
    String title;
    boolean approved;
    boolean confirmed;

    public Transaction(int id, double amount, String description, String title) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.title = title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
