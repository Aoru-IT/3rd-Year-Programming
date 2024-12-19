package com.example.acs_loanapp;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private String empID;

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
}

