package com.example.implementfirebasedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText studentName,course;
    Button saveBtn;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        studentName = (EditText) findViewById(R.id.StudentName);
        course = (EditText) findViewById(R.id.Course);
    }
    public void onClickSave(View view) {
        String stName = studentName.getText().toString();
        String stCourse = course.getText().toString();
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("StudentName",stName);
        userMap.put("Course",stCourse);
        root.setValue(userMap);
    }
}