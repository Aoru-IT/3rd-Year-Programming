package com.example.acs_loanapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class RegistrationActivity extends AppCompatActivity {


    DatabaseHelper myData = new DatabaseHelper(this);
    EditText firstName, middleName, lastName, employeeID, dateHired, password, confirmPassword;
    Button generate;
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

        firstName = findViewById(R.id.txtFirstName);
        middleName = findViewById(R.id.txtMiddleName);
        lastName = findViewById(R.id.txtLastName);
        employeeID = findViewById(R.id.txtEmployeeID);
        dateHired = findViewById(R.id.txtDateHired);
        password = findViewById(R.id.txtPassword);
        confirmPassword = findViewById(R.id.txtConfirmPassword);
        generate = findViewById(R.id.btnGenerate);


        firstName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (firstName.getText().toString().isEmpty()) {
                    firstName.setError("First name cannot be empty");
                }
            }
        });

        middleName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (middleName.getText().toString().isEmpty()) {
                    middleName.setError("Middle name cannot be empty");
                }
            }
        });

        lastName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (lastName.getText().toString().isEmpty()) {
                    lastName.setError("Last name cannot be empty");
                }
            }
        });

        employeeID.setFocusable(false);
        employeeID.setClickable(false);

        dateHired.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        calendar.set(selectedYear, selectedMonth, selectedDay);
                        String formattedDate = sdf.format(calendar.getTime());
                        dateHired.setText(formattedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });

    }

    public void myMessageWindow(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public boolean ValidateName() {
        boolean valid = true;
        if (firstName.getText().toString().isEmpty()) {
            firstName.setError("First name cannot be empty");
            valid = false;
        }

        if (middleName.getText().toString().isEmpty()) {
            middleName.setError("Middle name cannot be empty");
            valid = false;
        }

        if (lastName.getText().toString().isEmpty()) {
            lastName.setError("Last name cannot be empty");
            valid = false;
        }

        return valid;
    }

    public void clickLogin(View view) {
        Intent returnLoginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(returnLoginIntent);
        finish();
    }

    public void clickGenerate(View view) {
        if (!ValidateName()){return;}
        char initialFirst = firstName.getText().toString().toUpperCase().charAt(0);
        char initialMiddle = middleName.getText().toString().toUpperCase().charAt(0);
        char initialLast = lastName.getText().toString().toUpperCase().charAt(0);

        Random rand = new Random();
        employeeID.setText(String.format("%s%s%s%s", initialFirst, initialMiddle, initialLast, rand.nextInt(90000) + 10000));
        generate.setVisibility(View.INVISIBLE);

    }

    public void clickRegis(View view) {
        if (!ValidateName()){return;}
        if(password.getText().length() < 8){
            password.setError("Password is too short!");
            return;
        }

        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Passwords do not match!");
            return;
        }

        if(myData.isRecordExisting(employeeID.getText().toString())){
            myMessageWindow("Message","Record Already Exists");
        }
        else{
            boolean isRecordSaved = myData.registerEmployee(employeeID.getText().toString(),
                    firstName.getText().toString(),
                    middleName.getText().toString(),
                    lastName.getText().toString(),
                    dateHired.getText().toString(),
                    password.getText().toString());

            if (isRecordSaved)
                Toast.makeText(this, "Account Registered!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "There has been a problem in your registration.", Toast.LENGTH_SHORT).show();
        }

        Intent loginIntent =  new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}