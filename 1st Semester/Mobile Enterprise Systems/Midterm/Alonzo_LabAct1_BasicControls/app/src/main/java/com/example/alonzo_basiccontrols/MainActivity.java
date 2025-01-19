package com.example.alonzo_basiccontrols;

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

    TextView userField;
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
        userField = (TextView) findViewById(R.id.userInput);
    }

    public void clickedOne(View view) {
        userField.setText(String.valueOf(userField.getText() + "1"));
    }

    public void clickedTwo(View view) {
        userField.setText(String.valueOf(userField.getText() + "2"));
    }

    public void clickedThree(View view) {
        userField.setText(String.valueOf(userField.getText() + "3"));
    }

    public void clickedFour(View view) {
        userField.setText(String.valueOf(userField.getText() + "4"));
    }

    public void clickedFive(View view) {
        userField.setText(String.valueOf(userField.getText() + "5"));
    }

    public void clickedSix(View view) {
        userField.setText(String.valueOf(userField.getText() + "6"));
    }

    public void clickedSeven(View view) {
        userField.setText(String.valueOf(userField.getText() + "7"));
    }

    public void clickedEight(View view) {
        userField.setText(String.valueOf(userField.getText() + "8"));
    }

    public void clickedNine(View view) {
        userField.setText(String.valueOf(userField.getText() + "9"));
    }

    public void clickedZero(View view) {
        userField.setText(String.valueOf(userField.getText() + "0"));
    }
}