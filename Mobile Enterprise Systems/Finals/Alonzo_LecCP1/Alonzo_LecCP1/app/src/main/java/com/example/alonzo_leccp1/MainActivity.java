package com.example.alonzo_leccp1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Defining data object of Class DataBaseHelper
    DataBaseHelper myData = new DataBaseHelper(this);

    EditText txtStudentID,txtFName,txtLName,textRecordID;
    GridView myRecordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtStudentID = (EditText)findViewById(R.id.editStudentID);
        txtFName = (EditText)findViewById(R.id.editFName);
        txtLName = (EditText)findViewById(R.id.editLName);
        textRecordID = (EditText)findViewById(R.id.editRecordID);
        myRecordList = (GridView) findViewById(R.id.myRecords);

    }

    //Message Window
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

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



    //Add Module - this module saves the record into created database table
    public void onClickAdd(View view)
    {
        if(myData.isRecordExist(txtStudentID.getText().toString()))
        {
            myMessageWindow("Message","Record Already Exist");
        }
        else {
            boolean isRecordSaved = myData.SaveNewRecord(txtStudentID.getText().toString(),
                    txtFName.getText().toString(),
                    txtLName.getText().toString());

            if (isRecordSaved)
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Record Not Saved", Toast.LENGTH_SHORT).show();
            txtStudentID.setText("");
            txtFName.setText("");
            txtLName.setText("");
        }
    }

    //View Module - this module display the database record/s
    public void onClickView(View view)
    {
        ArrayList<String> myList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        Cursor myDataResult = myData.ViewRecords();
        if(myDataResult.getCount()==0) {
            Toast.makeText(this, "No Record/s Found", Toast.LENGTH_SHORT).show();
            myList.clear();
            myRecordList.setAdapter(adapter);
        }
        else {
            //Display Records into ArrayList
            while (myDataResult.moveToNext()) {
                myList.add(myDataResult.getString(0));
                myList.add(myDataResult.getString(1));
                myList.add(myDataResult.getString(2));
                myList.add(myDataResult.getString(3));
                myRecordList.setAdapter(adapter);
            }
        }

    }

    //Edit Module - this module edits a record from a database table
    public void onClickEdit(View view) {

        boolean isUpdate = myData.UpdateRecord(textRecordID.getText().toString(),
                txtStudentID.getText().toString(),
                txtFName.getText().toString(),
                txtLName.getText().toString());
        if(isUpdate)
            showMessage("Update Record","Record Updated");
        else
            showMessage("Update Record","Record not Updated");
    }

    //Search Module - this module search a specific record from a database table
    public void onClickSearch(View view) {

        if(!myData.SearchRecord(textRecordID.getText().toString()))
            showMessage("Alert","No Records Found");
        else
        {
            txtStudentID.setText(myData.getStNum());
            txtFName.setText(myData.getStFName());
            txtLName.setText(myData.getStLName());
        }
    }

    //Delete Module - this module delete a specific record from a database table
    public void onClickDelete(View view) {

        int deletedRows =   myData.deleteData(txtStudentID.getText().toString());
        if(deletedRows > 0)
            myMessageWindow("Delete Record","Record Deleted");
        else
            myMessageWindow("Delete Record","Record not Found");

    }

}