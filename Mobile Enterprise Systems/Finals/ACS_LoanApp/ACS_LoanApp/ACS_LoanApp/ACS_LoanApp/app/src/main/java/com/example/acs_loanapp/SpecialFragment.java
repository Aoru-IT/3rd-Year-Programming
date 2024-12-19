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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SpecialFragment extends Fragment {

    private String employeeID;

    public SpecialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        employeeID = MainActivity.getEmployeeID();
        if (employeeID == null) {
            Log.e("SpecialLoanFragment", "Employee ID is null");
        }

        View view = inflater.inflate(R.layout.fragment_special, container, false);
        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());

        Button button1 = view.findViewById(R.id.btnDetails2);
        EditText amount = view.findViewById(R.id.loanAmount2);
        EditText months = view.findViewById(R.id.monthsNumber2);


        boolean isEligible = isEmployedForFiveYears(employeeID);
        if (!isEligible) {
            // Show the toast immediately if the employee is not eligible
            Toast.makeText(requireContext(), "Account should be 5 years and above to access the special loan.", Toast.LENGTH_SHORT).show();
            amount.setEnabled(false);  // Disable the fields
            months.setEnabled(false);
        } else {
            amount.setEnabled(true);
            months.setEnabled(true);
        }


        button1.setOnClickListener(v -> {
            if (!isEligible) {
                Toast.makeText(requireContext(), "Account should be 5 years and above to access the special loan.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Continue with loan form validation if eligible
            if (employeeID == null) {
                amount.setError("Required employee data is missing");
                return;
            }

            String loanAmountStr = amount.getText().toString();
            String monthsStr = months.getText().toString();
            if (loanAmountStr.isEmpty() || monthsStr.isEmpty()) {
                amount.setError("Please fill out loan amount and months field");
                return;
            }

            double loanAmount = Double.parseDouble(loanAmountStr);
            if (loanAmount < 50000 || loanAmount > 100000) {
                amount.setError("Allowable loan amount is between 50000 and 100000");
                return;
            }

            int monthsNumber = Integer.parseInt(monthsStr);
            if (monthsNumber < 1 || monthsNumber > 18) {
                months.setError("Number of months should be between 1 and 18");
                return;
            }

            // Proceed with loan calculation
            calculateLoan(employeeID, loanAmount, monthsNumber);
        });

        return view;
    }

    private void calculateLoan(String employeeID, double amount, int months) {
        Computations compute = new Computations();
        double interestRate = compute.SpecialLoan_InterestRate(months);
        double loanInterest = compute.SpecialLoan_LoanInterest(amount, months, interestRate);
        double totalAmount = compute.SpecialLoan_TotalAmount(amount, loanInterest);
        double monthlyAmortization = compute.SpecialLoan_MonthlyAmortization(totalAmount, months);

        Bundle bundle = new Bundle();
        bundle.putString("employeeID", employeeID);
        bundle.putDouble("loanAmount", amount);
        bundle.putInt("monthsNumber", months);
        bundle.putDouble("interestRate", interestRate);
        bundle.putDouble("loanInterest", loanInterest);
        bundle.putDouble("totalAmount", totalAmount);
        bundle.putDouble("monthlyAmortization", monthlyAmortization);
        bundle.putString("loanType", "Special");
        bundle.putString("loanStatus", "Pending");

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

    private boolean isEmployedForFiveYears(String employeeID) {

        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());

        boolean result = false;
        String dateHiredString = dbHelper.getDateHired(employeeID); // Fetching date as a String
        if (dateHiredString != null) {
            try {

                // Define formatter for MM/dd/yyyy
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate hiredDate = LocalDate.parse(dateHiredString, formatter);
                LocalDate currentDate = LocalDate.now();

                long yearsBetween = ChronoUnit.YEARS.between(hiredDate, currentDate);
                result = yearsBetween >= 5;
            } catch (Exception e) {
                e.printStackTrace(); // Handle parsing errors
            }
        }
        return result;
    }
}