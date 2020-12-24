package com.KozKuzCompany.users.student;

public class ContractStudent extends Student {

    private double payment;
    private double paymentArrears;

    ContractStudent(String name, String groupNumber, int id, int rating, double benefits) {
        super(name, groupNumber, id, rating, benefits);
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getPaymentArrears() {
        return paymentArrears;
    }

    public void setPaymentArrears(double paymentArrears) {
        this.paymentArrears = paymentArrears;
    }

    public void payTuitionPayment(double payment) {

    }
}
