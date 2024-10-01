package com.example.alonzo_registrationea1;

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

public class LoginPage extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    TextView txtError;
    String firstName, lastName, emailAddress, birthday, username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtError =  (TextView) findViewById(R.id.txtError);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            firstName = bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            emailAddress = bundle.getString("emailAddress");
            birthday = bundle.getString("birthday");
            username = bundle.getString("username");
            password = bundle.getString("password");
        }

    }

    public void clickLogin(View view) {

        if(!txtUsername.getText().toString().equals(username)){
            txtError.setText(String.format("%s","Invalid username!"));
            return;
        }
        else if(!txtPassword.getText().toString().equals(password)){
            txtError.setText(String.format("%s","Invalid password!"));
            return;
        }

        Intent loginIntent = new Intent(LoginPage.this, UserLogged.class);
        Bundle loginBundle = new Bundle();
        loginBundle.putString("firstName", firstName);
        loginBundle.putString("lastName", lastName);
        loginBundle.putString("emailAddress", emailAddress);
        loginBundle.putString("birthday", birthday);
        loginBundle.putString("username", txtUsername.getText().toString());
        loginBundle.putString("password", txtPassword.getText().toString());

        loginIntent.putExtras(loginBundle);
        startActivity(loginIntent);
        finish();
    }

    public void clickSignUp(View view) {
        Intent signupIntent = new Intent(LoginPage.this, MainActivity.class);
        startActivity(signupIntent);
        finish();
    }
}