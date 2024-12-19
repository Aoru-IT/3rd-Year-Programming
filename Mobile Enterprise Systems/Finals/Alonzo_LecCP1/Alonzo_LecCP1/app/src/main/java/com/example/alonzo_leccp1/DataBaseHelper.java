package com.example.alonzo_leccp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "StudentID";
    public static final String COL_3 = "FirstName";
    public static final String COL_4 = "LastName";

    private String stID;
    private String stNum;
    private String stFName;
    private String stLName;

    private String MyDataBase()
    {
        String dbDefinition;
        dbDefinition = "CREATE TABLE "+ TABLE_NAME +
                "(ID integer PRIMARY KEY AUTOINCREMENT,StudentID Text,FirstName Text,LastName Text)";
        return dbDefinition;
    }


    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creation of database
        sqLiteDatabase.execSQL(MyDataBase());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean SaveNewRecord(String stNumber,String fName,String lName)
    {
        SQLiteDatabase saveCmd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,stNumber);
        contentValues.put(COL_3,fName);
        contentValues.put(COL_4,lName);
        long result = saveCmd.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;

    }

    public Cursor ViewRecords()
    {
        SQLiteDatabase viewRecords = this.getWritableDatabase();
        Cursor  resultSet;
        resultSet = viewRecords.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return resultSet;
    }

    //Editing of Records using Update command

    public boolean UpdateRecord(String recordID,String studentID,String firstName,String lastName)
    {
        SQLiteDatabase editRecord = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,recordID);
        contentValues.put(COL_2,studentID);
        contentValues.put(COL_3,firstName);
        contentValues.put(COL_4,lastName);
        editRecord.update(TABLE_NAME, contentValues, "StudentID = ?",new String[] { studentID });
        return true;

    }
    public  boolean isRecordExist(String studentID)
    {
        boolean found = false;
        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery("Select * From "+ TABLE_NAME + " Where StudentID = " + studentID,null);
        while (resultSet.moveToNext()) {

            found=true;
            break;
        }
        return found;
    }
    public boolean SearchRecord(String recordId)
    {
        boolean found = false;
        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery("Select * From "+ TABLE_NAME + " Where ID = " + recordId,null);
        while (resultSet.moveToNext()) {
            stID = resultSet.getString(0);
            stNum = resultSet.getString(1);
            stFName = resultSet.getString(2);
            stLName = resultSet.getString(3);
            found=true;
            break;
        }
        return found;
    }


    public int  deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedItem;
        deletedItem =  db.delete(TABLE_NAME, "StudentID = ?",new String[] {id});
        return deletedItem;
    }


    //region GET Set Methods
    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStNum() {
        return stNum;
    }

    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    public String getStFName() {
        return stFName;
    }

    public void setStFName(String stFName) {
        this.stFName = stFName;
    }

    public String getStLName() {
        return stLName;
    }

    public void setStLName(String stLName) {
        this.stLName = stLName;
    }
    //endregion
}