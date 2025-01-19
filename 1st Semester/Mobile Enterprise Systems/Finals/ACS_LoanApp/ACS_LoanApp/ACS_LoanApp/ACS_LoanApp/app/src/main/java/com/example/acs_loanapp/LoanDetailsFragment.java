package com.example.acs_loanapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import java.util.Objects;

public class LoanDetailsFragment extends Fragment {

    private TextView loanAmountLabel;
    private TextView loanAmountTextView;
    private TextView monthsNumberLabel;
    private TextView monthsNumberTextView;
    private TextView serviceChargeLabel;
    private TextView serviceChargeTextView;
    private TextView interestRateLabel;
    private TextView interestRateTextView;
    private TextView loanInterestLabel;
    private TextView loanInterestTextView;
    private TextView cashAmountLabel;
    private TextView cashAmountTextView;
    private TextView payableAmountLabel;
    private TextView payableAmountTextView;
    private TextView totalAmountLabel;
    private TextView totalAmountTextView;
    private TextView takeHomeLoanLabel;
    private TextView takeHomeLoanTextView;
    private TextView monthlyAmortizationLabel;
    private TextView monthlyAmortizationTextView;
    private TextView paymentMethodTextView;

    public LoanDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_details, container, false);

        // Initialize views as before
        loanAmountLabel = view.findViewById(R.id.textLoanAmount);
        loanAmountTextView = view.findViewById(R.id.loanAmount);
        monthsNumberLabel = view.findViewById(R.id.textMonths);
        monthsNumberTextView = view.findViewById(R.id.MonthsToPay);
        serviceChargeLabel = view.findViewById(R.id.textServiceCharge);
        serviceChargeTextView = view.findViewById(R.id.serviceCharge);
        interestRateLabel = view.findViewById(R.id.textInterestRate);
        interestRateTextView = view.findViewById(R.id.interestRate);
        loanInterestLabel = view.findViewById(R.id.textLoanInterest);
        loanInterestTextView = view.findViewById(R.id.LoanInterest);
        cashAmountLabel = view.findViewById(R.id.textCashAmount);
        cashAmountTextView = view.findViewById(R.id.cashAmount);
        payableAmountLabel = view.findViewById(R.id.textPayableAmount);
        payableAmountTextView = view.findViewById(R.id.payableAmount);
        totalAmountLabel = view.findViewById(R.id.textTotalAmount);
        totalAmountTextView = view.findViewById(R.id.totalAmount);
        takeHomeLoanLabel = view.findViewById(R.id.textTakeHome);
        takeHomeLoanTextView = view.findViewById(R.id.takeHomeLoan);
        monthlyAmortizationLabel = view.findViewById(R.id.textAmortization);
        monthlyAmortizationTextView = view.findViewById(R.id.monthlyAmortization);
        paymentMethodTextView = view.findViewById(R.id.modeOfPayment);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String empID = bundle.getString("employeeID");
            String loanType = bundle.getString("loanType");
            double loanAmount = bundle.getDouble("loanAmount");
            int monthsNumber = bundle.getInt("monthsNumber");
            double serviceCharge = bundle.getDouble("serviceCharge");
            double interestRate = bundle.getDouble("interestRate");
            double loanInterest = bundle.getDouble("loanInterest");
            double cashAmount = bundle.getDouble("cashAmount");
            double payableAmount = bundle.getDouble("payableAmount");
            double totalAmount = bundle.getDouble("totalAmount");
            double takeHomeLoan = bundle.getDouble("takeHomeLoan");
            double monthlyAmortization = bundle.getDouble("monthlyAmortization");

            // Set visibility and text
            loanAmountTextView.setText(String.format(Locale.getDefault(), "%.2f", loanAmount));
            monthsNumberTextView.setText(String.valueOf(monthsNumber));
            serviceChargeTextView.setText(String.format(Locale.getDefault(), "%.2f", serviceCharge));
            interestRateTextView.setText(String.valueOf(interestRate));
            loanInterestTextView.setText(String.format(Locale.getDefault(), "%.2f", loanInterest));
            cashAmountTextView.setText(String.format(Locale.getDefault(), "%.2f", cashAmount));
            payableAmountTextView.setText(String.format(Locale.getDefault(), "%.2f", payableAmount));
            totalAmountTextView.setText(String.format(Locale.getDefault(), "%.2f", totalAmount));
            takeHomeLoanTextView.setText(String.format(Locale.getDefault(), "%.2f", takeHomeLoan));
            monthlyAmortizationTextView.setText(String.format(Locale.getDefault(), "%.2f", monthlyAmortization));

            // Set visibility for each field
            setVisibility(loanAmountLabel, loanAmountTextView, loanAmount);
            setVisibility(monthsNumberLabel, monthsNumberTextView, monthsNumber);
            setVisibility(serviceChargeLabel, serviceChargeTextView, serviceCharge);
            setVisibility(interestRateLabel, interestRateTextView, interestRate);
            setVisibility(loanInterestLabel, loanInterestTextView, loanInterest);
            setVisibility(cashAmountLabel, cashAmountTextView, cashAmount);
            setVisibility(payableAmountLabel, payableAmountTextView, payableAmount);
            setVisibility(totalAmountLabel, totalAmountTextView, totalAmount);
            setVisibility(takeHomeLoanLabel, takeHomeLoanTextView, takeHomeLoan);
            setVisibility(monthlyAmortizationLabel, monthlyAmortizationTextView, monthlyAmortization);


// Update payment method based on cash amount
            String paymentMethod = cashAmount == 0 ? "Installment" : "Cash"; // If cash amount is 0, set to installment, else set to cash
            paymentMethodTextView.setText("Mode of Payment: " + paymentMethod);


            Button btnApply = view.findViewById(R.id.btnApply);
            DatabaseHelper myData = new DatabaseHelper(requireContext());

            boolean alreadyApplied = myData.hasAppliedForLoan(empID);

            if(Objects.equals(myData.GetLoanStatus(empID), "Approved")) { alreadyApplied = true; }
            if(Objects.equals(myData.GetLoanStatus(empID), "Declined")){ alreadyApplied = false; }

            if (alreadyApplied) {
                btnApply.setText("Already Applied");
                btnApply.setEnabled(false);
            } else {
                btnApply.setOnClickListener(v -> {
                    myData.registerLoanApplication(empID, loanType, paymentMethod, loanAmount,
                            bundle.getDouble("cashAmount", 0.0), payableAmount, interestRate,
                            loanInterest, monthsNumber, 0.0, takeHomeLoan, serviceCharge,
                            monthlyAmortization, "Pending");

                    LoanStatusFragment loanStatusFragment = new LoanStatusFragment();
                    Bundle args = new Bundle();
                    args.putString("empID", empID);
                    loanStatusFragment.setArguments(args);

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, loanStatusFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                });
            }
        }

        return view;
    }


    // Helper method to set visibility based on value
    private void setVisibility(TextView label, TextView textView, double value) {
        int visibility = value == 0 ? View.GONE : View.VISIBLE;
        label.setVisibility(visibility);
        textView.setVisibility(visibility);
    }
}
