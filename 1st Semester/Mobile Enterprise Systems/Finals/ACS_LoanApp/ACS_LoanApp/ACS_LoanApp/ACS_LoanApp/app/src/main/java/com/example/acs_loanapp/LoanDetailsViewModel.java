package com.example.acs_loanapp;

import androidx.lifecycle.ViewModel;
import android.os.Bundle;

public class LoanDetailsViewModel extends ViewModel {
    private Bundle loanDetails;

    public void setLoanDetails(Bundle details) {
        loanDetails = details;
    }

    public Bundle getLoanDetails() {
        return loanDetails;
    }
}
