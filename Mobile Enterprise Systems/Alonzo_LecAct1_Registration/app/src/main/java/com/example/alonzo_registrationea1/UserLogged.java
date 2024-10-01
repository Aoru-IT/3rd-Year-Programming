package com.example.alonzo_registrationea1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class UserLogged extends AppCompatActivity {

    TextView txtFirstName, txtLastName, txtEmail, txtBirthday, txtUsername;
    String firstName, lastName, emailAddress, birthday, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_logged);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtBirthday = (TextView) findViewById(R.id.txtBirthday);
        txtUsername = (TextView) findViewById(R.id.txtUsername);


        Bundle loginBundle  = getIntent().getExtras();
        if (loginBundle != null) {
            firstName = loginBundle.getString("firstName");
            lastName = loginBundle.getString("lastName");
            emailAddress = loginBundle.getString("emailAddress");
            birthday = loginBundle.getString("birthday");
            username = loginBundle.getString("username");
        }
        txtFirstName.setText(String.format("First Name: %s", firstName));
        txtLastName.setText(String.format("Last Name: %s", lastName));
        txtEmail.setText(String.format("Email Address: %s", emailAddress));
        txtBirthday.setText(String.format("Birthday: %s", birthday));
        txtUsername.setText(String.format("Welcome, %s", username));
    }

    public void clickLogout(View view) {
        Intent logoutIntent = new Intent(UserLogged.this , LoginPage.class);
        startActivity(logoutIntent);
        finish();
    }
}