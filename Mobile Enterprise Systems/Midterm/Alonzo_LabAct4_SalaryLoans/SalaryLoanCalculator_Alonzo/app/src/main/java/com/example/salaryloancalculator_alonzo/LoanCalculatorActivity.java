package com.example.salaryloancalculator_alonzo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.Locale;

public class LoanCalculatorActivity extends AppCompatActivity {


    TextView fullName, emailDisplay, textBasicSalary, loanableAmount, interestRate,
             interest, serviceCharge, takeHomeLoan, monthlyAmortization, numberMonthsEmpty, loanAmountError;
    EditText numberOfMonths, loanAmount;
    private int userIndex;
    private double maximumLoanableAmount;
    DecimalFormat df = new DecimalFormat("#,###.####");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    fullName = (TextView) findViewById(R.id.textFullName);
    emailDisplay = (TextView) findViewById(R.id.textEmailAddress);
    textBasicSalary = (TextView) findViewById(R.id.textBasicMonthlySalary);
    loanableAmount = (TextView) findViewById(R.id.textLoanableAmount);
    interestRate = (TextView) findViewById(R.id.textInterestRate);
    interest = (TextView) findViewById(R.id.textInterest);
    serviceCharge = (TextView) findViewById(R.id.textServiceCharge);
    takeHomeLoan = (TextView) findViewById(R.id.textTakeHomeLoan);
    monthlyAmortization = (TextView) findViewById(R.id.textMonthlyAmortization);
    numberMonthsEmpty = (TextView) findViewById(R.id.numberMonthsEmpty);
    loanAmountError = (TextView) findViewById(R.id.loanAmountError);

    numberOfMonths = (EditText) findViewById(R.id.editNumberOfMonths);
    loanAmount = (EditText) findViewById(R.id.editLoanAmount);

    Computations computations = Computations.getInstance();
    String email = getIntent().getStringExtra("email");
    userIndex = computations.getUserIndexByEmail(email);
    maximumLoanableAmount = computations.getLoanAmount(userIndex);

    fullName.setText(computations.getFullName(userIndex));

    emailDisplay.setText(String.format("Email: %s", computations.getEmail(userIndex)));
    textBasicSalary.setText(String.format("Php %s",df.format(computations.getBasicMonthlySalary(userIndex))));
    loanableAmount.setText(String.format("Php %s",df.format(maximumLoanableAmount)));
    interestRate.setText(String.format(Locale.US,"%.2f%%", computations.getInterestRate(userIndex) * 100));
    interest.setText(String.format("Php %s",df.format(computations.getInterest(userIndex))));
    serviceCharge.setText(String.format("Php %s",df.format(computations.getServiceCharge(userIndex))));
    takeHomeLoan.setText(String.format("Php %s",df.format(computations.getTakeHomeLoan(userIndex))));
    monthlyAmortization.setText(String.format("Php %s",df.format(computations.getMonthlyAmortization(userIndex))));

    }

    public void onClickCompute(View view) {
        resetErrorMessages();
        boolean hasError = errorTrapping();
        if (hasError) return;

        Computations computations = Computations.getInstance();
        computations.activityComputation(userIndex, Double.parseDouble(loanAmount.getText().toString()), Integer.parseInt(numberOfMonths.getText().toString()));

        interestRate.setText(String.format(Locale.US,"%.2f%%", computations.getInterestRate(userIndex) * 100));
        interest.setText(String.format("Php %s",df.format(computations.getInterest(userIndex))));
        serviceCharge.setText(String.format("Php %s",df.format(computations.getServiceCharge(userIndex))));
        takeHomeLoan.setText(String.format("Php %s",df.format(computations.getTakeHomeLoan(userIndex))));
        monthlyAmortization.setText(String.format("Php %s",df.format(computations.getMonthlyAmortization(userIndex))));
    }

    public void onClickLogout(View view) {
        Intent signOutIntent = new Intent(LoanCalculatorActivity.this, MainActivity.class);
        startActivity(signOutIntent);
        finish();
    }

    private boolean errorTrapping() {
        boolean hasError = false;

        if (numberOfMonths.getText().toString().isEmpty()) {
            numberMonthsEmpty.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (loanAmount.getText().toString().isEmpty()) {
            loanAmountError.setText(String.format("%s","X Required"));
            loanAmountError.setVisibility(View.VISIBLE);
            hasError = true;
        }
        else if(Double.parseDouble(loanAmount.getText().toString()) > maximumLoanableAmount){
             loanAmountError.setText(String.format("%s", "Exceeds loan limit"));
             loanAmountError.setVisibility(View.VISIBLE);
             hasError = true;
        }

        return hasError;
    }

    private void resetErrorMessages() {
        numberMonthsEmpty.setVisibility(View.GONE);
        loanAmountError.setVisibility(View.GONE);
    }



}