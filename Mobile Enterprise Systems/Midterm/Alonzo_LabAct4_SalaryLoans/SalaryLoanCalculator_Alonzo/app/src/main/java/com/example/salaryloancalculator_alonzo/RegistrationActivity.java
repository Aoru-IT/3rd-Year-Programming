package com.example.salaryloancalculator_alonzo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName, basicMonthlySalary, emailAddress, password, confirmPassword;
    TextView firstNameError, lastNameError, salaryEmpty, emailError, passwordEmpty, passwordError, confirmPassEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firstName = (EditText) findViewById(R.id.editFirstName);
        lastName = (EditText) findViewById(R.id.editLastName);
        basicMonthlySalary = (EditText) findViewById(R.id.editBasicMonthlySalary);
        emailAddress = (EditText) findViewById(R.id.editEmailAddress);
        password = (EditText) findViewById(R.id.editPassword);
        confirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        firstNameError = (TextView) findViewById(R.id.firstNameError);
        lastNameError= (TextView) findViewById(R.id.lastNameError);
        salaryEmpty = (TextView) findViewById(R.id.basicSalaryEmpty);
        emailError = (TextView) findViewById(R.id.emailError);
        passwordError = (TextView) findViewById(R.id.passwordError);
        passwordEmpty = (TextView) findViewById(R.id.passwordEmpty);
        confirmPassEmpty =(TextView) findViewById(R.id.confirmPassEmpty);
    }

    public void onClickReturnLogin(View view) {
        Intent returnLoginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
        returnLoginIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(returnLoginIntent);
    }

    public void onClickRegister(View view) {
        if (errorTrapping()) return;

        Computations computations = Computations.getInstance();

        computations.addRegistration(firstName.getText().toString(), lastName.getText().toString(),
                Double.parseDouble(basicMonthlySalary.getText().toString()), emailAddress.getText().toString(),
                password.getText().toString());

        Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_LONG).show();

        Intent returnLoginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(returnLoginIntent);
        finish();
    }

    private boolean errorTrapping() {
        boolean hasError = false;

        resetErrorMessages();

        if (firstName.getText().toString().isEmpty()) {
            firstNameError.setText(String.format("%s", "X Required"));
            firstNameError.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (!isLettersOnly(firstName.getText().toString())) {
            firstNameError.setText(String.format("%s", "can only be letters"));
            firstNameError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (lastName.getText().toString().isEmpty()) {
            lastNameError.setText(String.format("%s", "X Required"));
            lastNameError.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (!isLettersOnly(lastName.getText().toString())) {
            lastNameError.setText(String.format("%s", "can only be letters"));
            lastNameError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (basicMonthlySalary.getText().toString().isEmpty()) {
            salaryEmpty.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (emailAddress.getText().toString().isEmpty()) {
            emailError.setText(String.format("%s", "X Required"));
            emailError.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches()) {
            emailError.setText(String.format("%s", "Invalid Email"));
            emailError.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (Computations.getInstance().emailExists(emailAddress.getText().toString())) {
            emailError.setText(String.format("%s","Email already exists"));
            emailError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (password.getText().toString().isEmpty()) {
            passwordEmpty.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (password.length() < 8) {
            passwordError.setText(String.format("%s", "Password must be 8 characters long"));
            passwordError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (confirmPassword.getText().toString().isEmpty()) {
            confirmPassEmpty.setVisibility(View.VISIBLE);
            hasError = true;
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            if (password.length() >= 8) {
                passwordError.setText(String.format("%s","Passwords do not match"));
                passwordError.setVisibility(View.VISIBLE);
            }
            hasError = true;
        }

        return hasError;
    }

    private void resetErrorMessages() {
        firstNameError.setVisibility(View.GONE);
        lastNameError.setVisibility(View.GONE);
        salaryEmpty.setVisibility(View.GONE);
        emailError.setVisibility(View.GONE);
        passwordEmpty.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);
        confirmPassEmpty.setVisibility(View.GONE);
    }

    private boolean isLettersOnly(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c))  {
                return false;
            }
        }
        return true;
    }
}