package com.example.acs_loanapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class LoanApplicationFragment extends Fragment {
    private String employeeID;
    private String loanType;
    private String loanStatus;

    public LoanApplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            employeeID = getArguments().getString("employeeID");
            loanType = getArguments().getString("loanType");
            loanStatus = getArguments().getString("loanStatus");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_application, container, false);
        DatabaseHelper myData = new DatabaseHelper(requireContext());

        Button goBack = view.findViewById(R.id.btnGoBack);

        goBack.setOnClickListener(v -> {
            // Navigate to PendingFragment (Pending Loans page)
            PendingFragment pendingFragment = new PendingFragment();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, pendingFragment);  // Replace current fragment with PendingFragment
            transaction.addToBackStack(null);  // Optional: Add this transaction to the back stack
            transaction.commit();
        });

        // Declare and initialize your TextViews
        TextView employeeIDTextView = view.findViewById(R.id.employeeID);
        TextView loanAmountTextView = view.findViewById(R.id.loanAmount);
        TextView loanTypeTextView = view.findViewById(R.id.loanType);
        TextView paymentTypeTextView = view.findViewById(R.id.modeOfPayment);
        TextView serviceChargeTextView = view.findViewById(R.id.serviceCharge);
        TextView interestRateTextView = view.findViewById(R.id.interestRate);
        TextView loanInterestTextView = view.findViewById(R.id.LoanInterest);
        TextView numOfMosToPayTextView = view.findViewById(R.id.monthsNumber);
        TextView payableAmountTextView = view.findViewById(R.id.payableAmount);
        TextView cashTextView = view.findViewById(R.id.cashAmount);
        TextView totalAmountOfLoanTextView = view.findViewById(R.id.totalAmount);
        TextView monthlyAmortizationTextView = view.findViewById(R.id.monthlyAmortization);
        TextView takeHomeLoanTextView = view.findViewById(R.id.takeHomeLoan);
        TextView labelServiceCharge = view.findViewById(R.id.textServiceCharge);
        TextView labelPayableAmount = view.findViewById(R.id.textPayableAmount);


        Cursor cursor = myData.viewLoanDetails(employeeID);

        if (cursor.moveToFirst()) {
            int employeeIDIndex = cursor.getColumnIndex("EmployeeID");
            int loanAmountIndex = cursor.getColumnIndex("LoanAmount");
            int loanTypeIndex = cursor.getColumnIndex("LoanType");
            int paymentTypeIndex = cursor.getColumnIndex("PaymentType");

            if (employeeIDIndex >= 0) {
                employeeIDTextView.setText(cursor.getString(employeeIDIndex));
            }
            if (loanAmountIndex >= 0) {
                loanAmountTextView.setText(cursor.getString(loanAmountIndex));
            }
            if (loanTypeIndex >= 0) {
                loanTypeTextView.setText(cursor.getString(loanTypeIndex));
            }
            if (paymentTypeIndex >= 0) {
                String paymentType = cursor.getString(paymentTypeIndex);
                paymentTypeTextView.setText("Payment Type: " + paymentType);
            } else {
                Log.e("Error", "Column 'PaymentType' not found in the cursor");
            }

            String paymentType = paymentTypeIndex >= 0 ? cursor.getString(paymentTypeIndex) : null;

            int serviceChargeIndex = cursor.getColumnIndex("ServiceCharge");
            if (serviceChargeIndex >= 0) {
                serviceChargeTextView.setText(cursor.getString(serviceChargeIndex));
                double serviceChargeValue =  cursor.getDouble(serviceChargeIndex);
                labelServiceCharge.setVisibility(serviceChargeValue > 0.0 ?  View.VISIBLE : View.GONE);
            }

            if (paymentType != null && !paymentType.equals("Cash")) {
                int interestRateIndex = cursor.getColumnIndex("InterestRate");
                int loanInterestIndex = cursor.getColumnIndex("LoanInterest");
                int numOfMosToPayIndex = cursor.getColumnIndex("NumOfMosToPay");
                int payableAmountIndex = cursor.getColumnIndex("PayableAmount");

                if (interestRateIndex >= 0) {
                    interestRateTextView.setText(cursor.getString(interestRateIndex));
                }
                if (loanInterestIndex >= 0) {
                    loanInterestTextView.setText(cursor.getString(loanInterestIndex));
                }
                if (numOfMosToPayIndex >= 0) {
                    numOfMosToPayTextView.setText(cursor.getString(numOfMosToPayIndex));
                }
                if (payableAmountIndex >= 0) {
                    double payableTextValue =  cursor.getDouble(payableAmountIndex);
                    labelPayableAmount.setVisibility(payableTextValue > 0.0 ?  View.VISIBLE : View.GONE);
                    payableAmountTextView.setText(cursor.getString(payableAmountIndex));
                }
            } else {
                int cashIndex = cursor.getColumnIndex("Cash");

                if (cashIndex >= 0) {
                    cashTextView.setText(cursor.getString(cashIndex));


                }
            }

            switch (loanType) {
                case "Special":
                    int totalAmountOfLoanIndex = cursor.getColumnIndex("TotalAmountOfLoan");
                    int monthlyAmortizationIndex = cursor.getColumnIndex("MonthlyAmortization");

                    if (totalAmountOfLoanIndex >= 0) {
                        totalAmountOfLoanTextView.setText(cursor.getString(totalAmountOfLoanIndex));
                    }
                    if (monthlyAmortizationIndex >= 0) {
                        monthlyAmortizationTextView.setText(cursor.getString(monthlyAmortizationIndex));
                    }
                    break;
                case "Regular":
                    int takeHomeLoanIndex = cursor.getColumnIndex("TakeHomeLoan");

                    if (takeHomeLoanIndex >= 0) {
                        takeHomeLoanTextView.setText(cursor.getString(takeHomeLoanIndex));
                    }
                    break;
            }
        }

        Button approve = view.findViewById(R.id.btnApprove);
        Button decline = view.findViewById(R.id.btnDecline);


        if(!loanStatus.equals("Pending")){
            approve.setVisibility(View.GONE);
            decline.setVisibility(View.GONE);
        }

        approve.setOnClickListener(v -> {

            myData.UpdateLoanStatus(employeeID, "Approved");

            PendingFragment pendingFragment = new PendingFragment();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, pendingFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        decline.setOnClickListener(v -> {

            myData.UpdateLoanStatus(employeeID, "Declined");

            PendingFragment pendingFragment = new PendingFragment();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, pendingFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        cursor.close();
        return view;
    }


}