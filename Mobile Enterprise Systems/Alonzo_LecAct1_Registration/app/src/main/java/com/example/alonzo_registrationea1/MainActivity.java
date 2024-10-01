package com.example.alonzo_registrationea1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, emailAddress, birthday, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firstName = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        emailAddress = findViewById(R.id.txtEmail);
        birthday = findViewById(R.id.txtBirthday);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

        birthday.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        calendar.set(selectedYear, selectedMonth, selectedDay);
                        String formattedDate = sdf.format(calendar.getTime());
                        birthday.setText(formattedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });
    }

    public void clickRegister(View view) {
        Intent intent = new Intent(MainActivity.this, LoginPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("firstName", firstName.getText().toString());
        bundle.putString("lastName", lastName.getText().toString());
        bundle.putString("emailAddress", emailAddress.getText().toString());
        bundle.putString("birthday", birthday.getText().toString());
        bundle.putString("username", username.getText().toString());
        bundle.putString("password", password.getText().toString());

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}