package com.example.hodaphone;

public class InstallmentandDate {

    String installment ;
    String date ;

    public InstallmentandDate(String installment, String date) {
        this.installment = installment;
        this.date = date;
    }

    public String getInstallment() {
        return installment;
    }

    public String getDate() {
        return date;
    }
}
