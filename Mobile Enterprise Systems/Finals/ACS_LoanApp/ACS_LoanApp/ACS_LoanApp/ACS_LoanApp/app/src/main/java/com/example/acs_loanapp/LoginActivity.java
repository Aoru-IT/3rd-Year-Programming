    package com.example.acs_loanapp;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class LoginActivity extends AppCompatActivity {

        DatabaseHelper myData = new DatabaseHelper(this);
        EditText employeeID, password;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_login);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            employeeID = findViewById(R.id.employeeID);
            password = findViewById(R.id.editPassword);

            employeeID.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    if (employeeID.getText().toString().isEmpty()) {
                        employeeID.setError("EmployeeID field cannot be empty");
                    }
                }
            });

            password.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    if (password.getText().toString().isEmpty()) {
                        password.setError("Password field cannot be empty");
                    }
                }
            });
        }

        public void clickRegister(View view) {
            Intent registrationIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(registrationIntent);
            finish();
        }


        public boolean ValidateFields() {
            boolean valid = true;
            if (employeeID.getText().toString().isEmpty()) {
                employeeID.setError("EmployeeID field cannot be empty");
                valid = false;
            }

            if (password.getText().toString().isEmpty()) {
                password.setError("Password field cannot be empty");
                valid = false;
            }

            return valid;
        }


        public void clickLog(View view) {
            if(!ValidateFields()){ return; }
            if (myData.isAdmin(employeeID.getText().toString())) {
                if (!myData.adminPassCorrect(password.getText().toString())) {
                    password.setError("Password is incorrect, please try again.");
                    return;
                }
                Intent adminLoginIntent = new Intent(LoginActivity.this, AdminMainActivity.class);
                adminLoginIntent.putExtra("ID", employeeID.getText().toString());
                startActivity(adminLoginIntent);
                finish();
            } else {
                if (myData.isRecordExisting(employeeID.getText().toString())) {
                    if (myData.passwordMatches(employeeID.getText().toString(), password.getText().toString())) {
                        Intent employeeLoginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        employeeLoginIntent.putExtra("ID", employeeID.getText().toString());
                        employeeLoginIntent.putExtra("Name", myData.getFullName(employeeID.getText().toString()));
                        startActivity(employeeLoginIntent);
                        finish();
                    } else {
                        Toast.makeText(this, "Incorrect Password, please try again!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    employeeID.setError("Employee ID not found, please try again!");
                }
            }
        }
    }