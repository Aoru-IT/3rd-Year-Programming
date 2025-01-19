package com.example.acs_loanapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoanStatusFragment extends Fragment {

    private TextView textStatus;

    public LoanStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_status, container, false);

        textStatus = view.findViewById(R.id.textStatus);

        String empID = MainActivity.getEmployeeID();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        String loanStatus = databaseHelper.GetLoanStatus(empID);
        textStatus.setText(loanStatus);

        return view;
    }
}