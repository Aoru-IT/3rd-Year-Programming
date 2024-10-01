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

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    TextView emailError, passwordError;
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

        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPassword);

        emailError = (TextView) findViewById(R.id.emailError);
        passwordError = (TextView) findViewById(R.id.passwordError);
    }

    public void onClickLogin(View view) {
        resetErrorMessages();

        boolean hasError = errorTrapping();
        if (hasError) return;

        Computations computations = Computations.getInstance();
        int loginOutput = computations.login(email.getText().toString(), password.getText().toString());

        switch (loginOutput) {
            case 0:
                Intent loginIntent = new Intent(MainActivity.this, LoanCalculatorActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                loginIntent.putExtra("email", email.getText().toString());

                startActivity(loginIntent);
                finish();
                break;
            case 1:
                emailError.setText(String.format("%s","Email doesn't exist"));
                emailError.setVisibility(View.VISIBLE);
                break;
            case 2:
                passwordError.setText(String.format("%s","Incorrect Password"));
                passwordError.setVisibility(View.VISIBLE);
                break;
        }
    }

    private boolean errorTrapping() {
        boolean hasError = false;

        if (email.getText().toString().isEmpty()) {
            emailError.setText(String.format("%s","X Required"));
            emailError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        if (password.getText().toString().isEmpty()) {
            passwordError.setText(String.format("%s","X Required"));
            passwordError.setVisibility(View.VISIBLE);
            hasError = true;
        }

        return hasError;
    }

    private void resetErrorMessages() {
        emailError.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);
    }


    public void onClickSignUp(View view) {
        Intent signUpIntent = new Intent(MainActivity.this, RegistrationActivity.class);
        signUpIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(signUpIntent);
    }
}