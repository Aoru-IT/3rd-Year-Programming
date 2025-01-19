package com.example.alonzo_bmi;

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

    EditText weight, height;
    TextView bmi, classification;
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
        weight = (EditText) findViewById(R.id.txtWeight);
        height = (EditText) findViewById(R.id.txtHeight);
        bmi = (TextView) findViewById(R.id.txtBMI);
        classification = (TextView) findViewById(R.id.txtClassification);

    }

    public void clickCalculate(View view) {
        double bmiResult = bmiCalculator();
        bmi.setText(String.format("BMI: %s", bmiResult));
        String bmiClassification;
        
        if(bmiResult < 18.5){
            bmiClassification = "Underweight";
        }
        else if(bmiResult >= 18.5 && bmiResult < 25){
            bmiClassification = "Normal";
        }
        else if(bmiResult >= 25 && bmiResult < 30){
            bmiClassification = "Overweight";
        }
        else{
            bmiClassification = "Obese";
        }

        classification.setText(String.format("Classification: %s", bmiClassification));
    }
    
    double bmiCalculator(){
        return (Double.parseDouble(weight.getText().toString()) * 703) / (Math.pow(Double.parseDouble(height.getText().toString()), 2));
    }
}