package com.example.acs_loanapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RegularFragment extends Fragment {
    private static final String TAG = "RegularFragment";
    private String employeeID;

    public RegularFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regular, container, false);

        Computations compute = new Computations();
        employeeID = MainActivity.getEmployeeID();

        if (employeeID == null) {
            Toast.makeText(getContext(), "Error: Employee ID not found", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Employee ID is null");
            return view;
        }

        Button enterSalary = view.findViewById(R.id.btnEnterSalary);
        EditText txtSalary = view.findViewById(R.id.txtSalary);
        TextView loanableAmount = view.findViewById(R.id.loanableAmount);
        EditText months = view.findViewById(R.id.txtMonths);
        EditText loanAmount = view.findViewById(R.id.txtAmToLoan);
        Button viewLoanDetails = view.findViewById(R.id.btnViewLoanDetails);
        Button clear = view.findViewById(R.id.btnClear);

        // Initially hide the elements
        months.setVisibility(View.GONE);
        loanAmount.setVisibility(View.GONE);
        loanableAmount.setVisibility(View.GONE);
        viewLoanDetails.setVisibility(View.GONE);

        enterSalary.setOnClickListener(v -> {
            String salaryText = txtSalary.getText().toString();

            if (salaryText.isEmpty()) {
                txtSalary.setError("Please enter your salary");
                Log.w(TAG, "Salary input is empty");
                return;
            }


            try {
                double salary = Double.parseDouble(salaryText);
                double loanableAm = compute.RegularLoan_LoanableAmount(salary);

                if (salary <= 0){
                    txtSalary.setError("Please enter a valid salary");
                    Log.w(TAG, "Salary should be greater than 0");
                    return;
                }

                loanableAmount.setText("Loanable Amount: " + loanableAm);
                loanableAmount.setVisibility(View.VISIBLE);
                months.setVisibility(View.VISIBLE);
                loanAmount.setVisibility(View.VISIBLE);
                viewLoanDetails.setVisibility(View.VISIBLE);

                txtSalary.setText(salaryText);
                txtSalary.setEnabled(false);
                enterSalary.setVisibility(View.GONE);

            } catch (NumberFormatException e) {
                Log.e(TAG, "Invalid input for salary", e);
                txtSalary.setError("Please enter a valid salary");
            }
        });

        viewLoanDetails.setOnClickListener(v -> {
            String loanAmountText = loanAmount.getText().toString();
            String monthsText = months.getText().toString();

            if (loanAmountText.isEmpty()) {
                loanAmount.setError("Please enter loan amount.");
                Log.w(TAG, "Loan amount input is empty");
                return;
            }


            if (monthsText.isEmpty()) {
                months.setError("Please enter the number of months.");
                Log.w(TAG, "Months input is empty");
                return;
            }

            try {
                double loanAm = Double.parseDouble(loanAmountText);
                int numMonths = Integer.parseInt(monthsText);
                double loanableAm = Double.parseDouble(loanableAmount.getText().toString().split(": ")[1]);

                if (loanAm > loanableAm) {
                    loanAmount.setError("Loan amount exceeds loanable amount.");
                    Log.w(TAG, "Loan amount exceeds loanable amount");
                    return;
                }

                if (loanAm <= 0){
                    loanAmount.setError("Loan amount should be greater than 0");
                    Log.w(TAG, "Loan amount should be greater than 0");
                    return;
                }

                if(numMonths < 1 || numMonths > 24){
                    months.setError("Number of months should be between 1 and 24");
                    Log.w(TAG, "Number of months should be between 1 and 6");
                    return;
                }

                calculateLoan(employeeID, loanAm, numMonths);

            } catch (NumberFormatException e) {
                Log.e(TAG, "Invalid input for loan amount or months", e);
                Toast.makeText(getContext(), "Please enter valid inputs", Toast.LENGTH_SHORT).show();
            }
        });

        clear.setOnClickListener(v ->{
            txtSalary.setEnabled(true);
            txtSalary.setText("");
            enterSalary.setVisibility(View.VISIBLE);
            months.setVisibility(View.GONE);
            months.setText("");
            loanAmount.setVisibility(View.GONE);
            loanAmount.setText("");
            loanableAmount.setVisibility(View.GONE);
            loanableAmount.setText("");
            viewLoanDetails.setVisibility(View.GONE);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setCheckedItem(R.id.nav_regular);
        }
    }

    private void calculateLoan(String employeeID, double amount, int months) {
        Computations compute = new Computations();

        double interestRate =(compute.RegularLoan_InterestRate(months));
        double loanInterest = roundToTwoDecimals(compute.RegularLoan_LoanInterest(amount, months, interestRate));
        double serviceCharge = roundToTwoDecimals(compute.RegularLoan_ServiceCharge(amount));
        double takeHomeLoan = roundToTwoDecimals(compute.RegularLoan_TakeHomeLoan(amount, serviceCharge, loanInterest));
        double monthlyAmortization = roundToTwoDecimals(compute.RegularLoan_MonthlyAmortization(takeHomeLoan, months));

        Bundle bundle = new Bundle();
        bundle.putString("employeeID", employeeID);
        bundle.putDouble("loanAmount", amount);
        bundle.putInt("monthsNumber", months);
        bundle.putDouble("serviceCharge", serviceCharge);
        bundle.putDouble("interestRate", interestRate);
        bundle.putDouble("loanInterest", loanInterest);
        bundle.putDouble("takeHomeLoan", takeHomeLoan);
        bundle.putDouble("monthlyAmortization", monthlyAmortization);
        bundle.putString("paymentMethod", "Installment");
        bundle.putString("loanType", "Regular");
        bundle.putString("loanStatus", "Pending");

        LoanDetailsViewModel model = new ViewModelProvider(requireActivity()).get(LoanDetailsViewModel.class);
        model.setLoanDetails(bundle);

        LoanDetailsFragment loanDetailsFragment = new LoanDetailsFragment();
        loanDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, loanDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        Log.d(TAG, "Loan calculation completed and fragment transaction initiated.");
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
