package com.example.acs_loanapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class EmergencyFragment extends Fragment {

    private String employeeID;

    public EmergencyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        employeeID = MainActivity.getEmployeeID();
        if (employeeID == null) {
            Log.e("EmergencyFragment", "Employee ID is null");
        }

        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        Button button1 = view.findViewById(R.id.btnDetails);
        EditText amount = view.findViewById(R.id.loanAmount);
        EditText months = view.findViewById(R.id.monthsNumber);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = view.findViewById(checkedId);
            if (checkedRadioButton != null) {
                String selectedPaymentMethod = checkedRadioButton.getText().toString();
                if (selectedPaymentMethod.equals("Cash")) {
                    months.setText("0");
                    months.setVisibility(View.GONE);
                } else {
                    months.setVisibility(View.VISIBLE);
                }
            }
        });

        button1.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = view.findViewById(selectedId);

            if (selectedId == -1) {
                Toast.makeText(getActivity(), "Please select a payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            String paymentMethod = selectedRadioButton.getText().toString();

            if (employeeID == null) {
                amount.setError("Required employee data is missing");
                return;
            }

            String loanAmountStr = amount.getText().toString();
            String monthsStr = months.getText().toString();
            if (loanAmountStr.isEmpty() || (paymentMethod.equals("Cash") && monthsStr.isEmpty())) {
                amount.setError("Please fill out loan amount and months field");
                return;
            }

            double loanAmount = Double.parseDouble(loanAmountStr);
            if (loanAmount < 5000 || loanAmount > 25000) {
                amount.setError("Allowable loan amount is between 5000 and 25000");
                return;
            }

            if (paymentMethod.equals("Installment") && monthsStr.isEmpty()) {
                months.setError("Please enter the number of months");
                return;
            }

            int monthsNumber = paymentMethod.equals("Cash") || paymentMethod.equals("Installment") ? Integer.parseInt(monthsStr) : 0;
            if (paymentMethod.equals("Installment") && (monthsNumber < 1 || monthsNumber > 6)) {
                months.setError("Number of months should be between 1 and 6");
                return;
            }

            calculateLoan(employeeID, loanAmount, monthsNumber, paymentMethod);
        });
        return view;
    }

    private void calculateLoan(String employeeID, double amount, int months, String paymentMethod) {
        Computations compute = new Computations();
        double serviceCharge = compute.EmergencyLoan_ServiceCharge(amount);
        double interestRate = compute.EmergencyLoan_InterestRate(amount, months);
        double loanInterest = compute.EmergencyLoan_LoanInterest(amount, months);
        double cashAmount = compute.EmergencyLoan_Cash(amount, serviceCharge);
        double payableAmount = compute.EmergencyLoan_PayableAmount(amount, serviceCharge, loanInterest, months);
        double takeHomeLoan = compute.EmergencyLoan_TakeHomeLoan(amount, serviceCharge, loanInterest);
        double monthlyAmortization = compute.EmergencyLoan_MonthlyAmortization(takeHomeLoan, months);

        Bundle bundle = new Bundle();
        bundle.putString("employeeID", employeeID);
        bundle.putDouble("loanAmount", amount);
        bundle.putInt("monthsNumber", months);
        bundle.putDouble("serviceCharge", serviceCharge);
        bundle.putDouble("interestRate", interestRate);
        bundle.putDouble("loanInterest", loanInterest);
        bundle.putDouble("cashAmount", paymentMethod.equals("Cash") ? cashAmount : 0);
        bundle.putDouble("payableAmount", payableAmount);
        bundle.putDouble("takeHomeLoan", 0.0);
        bundle.putDouble("monthlyAmortization", 0.0);
        bundle.putString("paymentMethod", paymentMethod);
        bundle.putString("loanType", "Emergency");
        bundle.putString("loanStatus", "Pending");

        // Update the LoanDetailsViewModel with the new loan details
        LoanDetailsViewModel model = new ViewModelProvider(requireActivity()).get(LoanDetailsViewModel.class);
        model.setLoanDetails(bundle);

        LoanDetailsFragment loanDetailsFragment = new LoanDetailsFragment();
        loanDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, loanDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_loan_details);
    }
}
