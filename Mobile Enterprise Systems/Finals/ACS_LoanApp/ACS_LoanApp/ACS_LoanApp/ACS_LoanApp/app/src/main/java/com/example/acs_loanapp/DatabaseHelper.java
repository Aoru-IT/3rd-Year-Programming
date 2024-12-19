package com.example.acs_loanapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ACS_AdminEmployeeDatabase.db";

    public static final String EMP_TABLE_NAME = "Employee_Table";
    public static final String ECOL_1 = "EmployeeID";
    public static final String ECOL_2 = "FirstName";
    public static final String ECOL_3 = "MiddleInitial";
    public static final String ECOL_4 = "LastName";
    public static final String ECOL_5 = "DateHired";
    public static final String ECOL_6 = "Password";
    public static final String ECOL_7 = "Salary";

    public static final String LOAN_TABLE_NAME = "Loan_Table";
    public static final String LCOL_1 = "ID";
    public static final String LCOL_2 = "EmployeeID";
    public static final String LCOL_3 = "LoanType";
    public static final String LCOL_4 = "PaymentType";
    public static final String LCOL_5 = "LoanAmount";
    public static final String LCOL_6 = "Cash";
    public static final String LCOL_7 = "PayableAmount";
    public static final String LCOL_8 = "InterestRate";
    public static final String LCOL_9 = "LoanInterest";
    public static final String LCOL_10 = "NumOfMosToPay";
    public static final String LCOL_11 = "TotalAmountOfLoan";
    public static final String LCOL_12 = "TakeHomeLoan";
    public static final String LCOL_13 = "ServiceCharge";
    public static final String LCOL_14 = "MonthlyAmortization";
    public static final String LCOL_15 = "LoanStatus";

    private String empID;
    private String empFName;
    private String empMInitial;
    private String empLName;
    private String empDateHired;
    private String empPassword;
    private double empSalary;

    private int autoLoanID;
    private String employeeID;
    private String loanType;
    private String paymentType;
    private double loanAmount;
    public double cash;
    public double payableAmount;
    private double interestRate;
    private double loanInterest;
    private int numOfMosToPay;
    private double totalAmountOfLoan;
    private double takeHomeLoan;
    private double serviceCharge;
    private double monthlyAmortization;
    private String loanStatus;

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM " + EMP_TABLE_NAME;
    private static final String SELECT_ALL_LOANS = "SELECT * FROM " + LOAN_TABLE_NAME;
    private static final String SELECT_APPROVED_LOANS = "SELECT ID, EmployeeID, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE LoanStatus = 'Approved'";
    private static final String SELECT_DISAPPROVED_LOANS = "SELECT ID, EmployeeID, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE LoanStatus = 'Disapproved'";


    private static final String CREATE_EMP_TABLE =
            "CREATE TABLE " + EMP_TABLE_NAME + " (" +
                    ECOL_1 + " TEXT PRIMARY KEY, " +
                    ECOL_2 + " TEXT, " +
                    ECOL_3 + " TEXT, " +
                    ECOL_4 + " TEXT, " +
                    ECOL_5 + " TEXT, " +
                    ECOL_6 + " TEXT, " +
                    ECOL_7 + " DOUBLE DEFAULT 0.0)";


    private static final String CREATE_LOAN_TABLE =
            "CREATE TABLE " + LOAN_TABLE_NAME + " (" +
                    LCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LCOL_2 + " TEXT, " +
                    LCOL_3 + " TEXT, " +
                    LCOL_4 + " TEXT, " +
                    LCOL_5 + " DOUBLE, " +
                    LCOL_6 + " DOUBLE, " +
                    LCOL_7 + " DOUBLE, " +
                    LCOL_8 + " DOUBLE, " +
                    LCOL_9 + " DOUBLE, " +
                    LCOL_10 + " INTEGER, " +
                    LCOL_11 + " DOUBLE, " +
                    LCOL_12 + " DOUBLE, " +
                    LCOL_13 + " DOUBLE, " +
                    LCOL_14 + " DOUBLE, " +
                    LCOL_15 + " TEXT DEFAULT 'Pending', " +
                    "FOREIGN KEY(" + LCOL_2 + ") REFERENCES " + EMP_TABLE_NAME + "(" + ECOL_1 + "))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        // Enable foreign key constraints
        db.setForeignKeyConstraintsEnabled(true);

        // Optional: Enable Write-Ahead Logging for better concurrency
        db.enableWriteAheadLogging();
    }



    // Create Employee and Loan Tables
    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EMP_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOAN_TABLE);

        String adminAcc = "INSERT INTO Employee_Table(EmployeeID, FirstName, MiddleInitial, LastName, DateHired, Password) " +
                "VALUES('ADM12345', 'Admin', 'A', 'Admin', '01/01/2023', 'admin12345')";
        sqLiteDatabase.execSQL(adminAcc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMP_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOAN_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    // Employee DB Procedures

    // Inserts record in Employee Table
    public boolean registerEmployee(String empID, String empFName, String empMInitial, String empLName, String empDateHired, String empPassword) {
        SQLiteDatabase saveCmd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ECOL_1, empID);
        contentValues.put(ECOL_2, empFName);
        contentValues.put(ECOL_3, empMInitial);
        contentValues.put(ECOL_4, empLName);
        contentValues.put(ECOL_5, empDateHired);
        contentValues.put(ECOL_6, empPassword);

        long result = saveCmd.insert(EMP_TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public boolean isAdmin(String empID) {
        return "ADM12345".equals(empID);
    }

    public boolean adminPassCorrect(String empPassword){
        return "admin12345".equals(empPassword);
    }

    // Checks if password matches on Employee Table
    @SuppressLint("Recycle")
    public boolean passwordMatches(String empID, String empPassword) {
        boolean found = false;
        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery(
                SELECT_ALL_EMPLOYEES + " WHERE EmployeeID = ? AND Password = ?",
                new String[]{empID, empPassword}
        );
        while (resultSet.moveToNext()) {
            found = true;
            break;
        }
        resultSet.close();
        return found;
    }

    // Checks if record exists in Employee Table
    @SuppressLint("Recycle")
    public boolean isRecordExisting(String empID) {
        boolean found = false;
        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery(SELECT_ALL_EMPLOYEES + " WHERE EmployeeID = ?", new String[]{empID});

        while (resultSet.moveToNext()) {
            found = true;
            break;
        }
        resultSet.close();
        return found;
    }


    public String getFullName(String empID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT FirstName, MiddleInitial, LastName FROM " + EMP_TABLE_NAME + " WHERE EmployeeID = ?", new String[]{empID});

        String fullName = "Unknown";
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(0);
            String middleInitial = cursor.getString(1);
            String lastName = cursor.getString(2);

            fullName = firstName + " ";
            if (middleInitial != null && !middleInitial.isEmpty()) {
                fullName += middleInitial + " ";
            }
            fullName += lastName;
        }

        cursor.close();

        return fullName;
    }

    public double getSalary(String empID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Salary FROM " + EMP_TABLE_NAME + " WHERE EmployeeID = ?", new String[]{empID});

        double salary = -1.0;
        if (!cursor.moveToFirst()) {
            salary = cursor.getDouble(0);
        }

        cursor.close();

        return salary;
    }

    public String getDateHired(String empID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DateHired FROM " + EMP_TABLE_NAME + " WHERE EmployeeID = ?", new String[]{empID});

        String dateHiredString = "";
        if (cursor != null && cursor.moveToFirst()) {
            dateHiredString = cursor.getString(0);
        }
        cursor.close();

        return dateHiredString;
    }



    @SuppressLint("Recycle")
    public boolean isLoanExisting(String empID) {
        boolean found = false;
        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery(SELECT_ALL_LOANS + " WHERE EmployeeID = ?", new String[]{empID});

        while (resultSet.moveToNext()) {
            found = true;
            break;
        }
        resultSet.close();
        return found;
    }

    public boolean hasAppliedForLoan(String empID) {
        boolean found = false;

        SQLiteDatabase readRecId = this.getWritableDatabase();
        Cursor resultSet;
        resultSet = readRecId.rawQuery(SELECT_ALL_LOANS + " WHERE EmployeeID = ? AND LoanStatus != 'Declined'", new String[]{empID});

        while (resultSet.moveToNext()) {
            found = true;
            break;
        }
        resultSet.close();
        return found;
    }

    public boolean UpdateSalary(String empID, double Salary) {
        SQLiteDatabase updateRecord = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ECOL_7, Salary);

        int rowsAffected = updateRecord.update(
                EMP_TABLE_NAME,
                contentValues,
                "EmployeeID = ?",
                new String[] { empID }
        );

        return rowsAffected > 0; // Return true only if at least one row was updated
    }


    public boolean registerLoanApplication(String empID, String loanType, String paymentType, double loanAmount, double cash, double payableAmount, double interestRate,
                                           double loanInterest, int numOfMosToPay, double totalAmountOfLoan, double takeHomeLoan, double serviceCharge,
                                           double monthlyAmortization, String loanStatus ) {
        SQLiteDatabase saveCmd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Employee ID (Foreign Key)
        contentValues.put(LCOL_2, empID);
        // Loan Type
        contentValues.put(LCOL_3, loanType);
        // Payment Type
        contentValues.put(LCOL_4, paymentType);
        // Loan Amount
        contentValues.put(LCOL_5, loanAmount);
        // Service Charge


        // For Emergency Loan: Handle logic for specific fields
        if (loanType.equals("Emergency")) {

            // Emergency loans might not have Monthly Amortization depending on payment type
            if (!paymentType.equals("Cash")) {
                contentValues.put(LCOL_7, payableAmount); // Payable Amount
                contentValues.put(LCOL_8, interestRate); // Interest Rate
                contentValues.put(LCOL_9, loanInterest); // Loan Interest
                contentValues.put(LCOL_10, numOfMosToPay); // Number of Months to Pay

            } else {
                contentValues.put(LCOL_6, cash);
            }

            contentValues.put(LCOL_13, serviceCharge); // Service Charge
        }

        // For Special Loan (special conditions for columns can go here)
        if (loanType.equals("Special")) {

            contentValues.put(LCOL_8, interestRate); // Interest Rate
            contentValues.put(LCOL_9, loanInterest); // Loan Interest
            contentValues.put(LCOL_10, numOfMosToPay); // Number of Months to Pay
            contentValues.put(LCOL_11, totalAmountOfLoan); // Total Amount of Loan
            contentValues.put(LCOL_14, monthlyAmortization); // Monthly Amortization
        }

        // For Regular Loan (populate all fields as needed)
        if (loanType.equals("Regular")) {

            contentValues.put(LCOL_8, interestRate); // Interest Rate
            contentValues.put(LCOL_9, loanInterest); // Loan Interest
            contentValues.put(LCOL_10, numOfMosToPay); // Number of Months to Pay
            contentValues.put(LCOL_12, takeHomeLoan); // Take Home Loan
            contentValues.put(LCOL_13, serviceCharge); // Service Charge
            contentValues.put(LCOL_14, monthlyAmortization); // Monthly Amortization
        }

        // Loan Status (default value or specified by user)
        contentValues.put(LCOL_15, loanStatus); // Loan Status

        long result = saveCmd.insert(LOAN_TABLE_NAME, null, contentValues);

        return result != -1;  // -1 means failure
    }


    // Updates Loan Status

    public boolean UpdateLoanStatus(String empID, String loanStatus) {
        SQLiteDatabase updateRecord = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LCOL_15, loanStatus);

        int rowsAffected = updateRecord.update(
                LOAN_TABLE_NAME,
                contentValues,
                "EmployeeID = ?",
                new String[] { empID }
        );

        return rowsAffected > 0; // Return true only if at least one row was updated
    }

    // Views all Employee details except password
    public Cursor ViewAllRecordDetails() {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        return viewRecords.rawQuery("SELECT EmployeeID, FirstName, MiddleInitial, LastName, DateHired FROM " + EMP_TABLE_NAME + " ORDER BY EmployeeID ASC", null);
    }


    public Cursor viewLoanDetails(String empID) {
        SQLiteDatabase viewRecords = this.getReadableDatabase();

        // Select all columns from the loan table for the specified EmployeeID
        String query = "SELECT EmployeeID, LoanType, LoanAmount, NumOfMosToPay, ServiceCharge, InterestRate, LoanInterest, PayableAmount, TakeHomeLoan, MonthlyAmortization, PaymentType, Cash FROM " + LOAN_TABLE_NAME + " WHERE EmployeeID = ? ORDER BY ID ASC";

        // Execute the query
        return viewRecords.rawQuery(query, new String[]{empID});
    }



    //Returns only the loan status
    public String GetLoanStatus(String empID) {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        Cursor cursor = viewRecords.rawQuery(
                "SELECT LoanStatus FROM " + LOAN_TABLE_NAME +
                        " WHERE EmployeeID = ?" +
                        " ORDER BY CASE WHEN LoanStatus = 'Pending' THEN 0 ELSE 1 END", new String[]{empID});

        String loanStatus = null;
        if (cursor.moveToFirst()) {
            loanStatus = cursor.getString(0);
        }
        cursor.close();
        return loanStatus;
    }

    //Returns only the loan type
    public String GetLoanType(String empID) {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        Cursor cursor = viewRecords.rawQuery(
                "SELECT LoanType FROM " + LOAN_TABLE_NAME + " WHERE EmployeeID = ?", new String[]{empID});

        String loanType = null;
        if (cursor.moveToFirst()) {
            loanType = cursor.getString(0);
        }
        cursor.close();
        return loanType;
    }

    // Returns the PaymentType
    public String GetPaymentType(String empID) {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        Cursor cursor = viewRecords.rawQuery(
                "SELECT PaymentType FROM " + LOAN_TABLE_NAME + " WHERE EmployeeID = ?", new String[]{empID});

        String paymentType = null;
        if (cursor.moveToFirst()) {
            paymentType = cursor.getString(0);
        }
        cursor.close();
        return paymentType;
    }

    // Admin View on Loans
    public Cursor Admin_ViewLoans() {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        return viewRecords.rawQuery("SELECT ID, EmployeeID, LoanType, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " ORDER BY ID ASC", null);
    }

    // Returns all loans with "approved" status
    public Cursor ViewAllApprovedLoans() {
        SQLiteDatabase viewRecords = this.getWritableDatabase();
        return viewRecords.rawQuery(
                "SELECT ID, EmployeeID, LoanType, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE LoanStatus = 'Approved' ORDER BY ID ASC ", null);
    }


    // Returns all loans with "disapproved" status
    public Cursor ViewAllDisapprovedLoans() {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        return viewRecords.rawQuery(
                "SELECT ID, EmployeeID, LoanType, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE LoanStatus = 'Declined' ORDER BY ID ASC ", null);
    }

    public Cursor ViewAllPendingLoans() {
        SQLiteDatabase viewRecords = this.getReadableDatabase();
        return viewRecords.rawQuery(
                "SELECT ID, EmployeeID, LoanType, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE LoanStatus = 'Pending' ORDER BY ID ASC ", null);
    }

    // Searches employee record on Employee Table
    public boolean searchEmployeeRecord(String employeeID) {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("SELECT ID, FirstName, MiddleInitial, LastName, DateHired, Salary FROM "+ EMP_TABLE_NAME +" WHERE EmployeeID = ?", new String[]{employeeID});

        if (resultSet.moveToFirst()) {
            empID = resultSet.getString(0);
            empFName = resultSet.getString(1);
            empMInitial = resultSet.getString(2);
            empLName = resultSet.getString(3);
            empDateHired = resultSet.getString(4);
            empSalary = resultSet.getDouble(5);
            found = true;
        }
        resultSet.close();
        return found;
    }

    //Searches loan record on Loan Table
    public boolean searchLoanRecord(String empID) {
        boolean found = false;
        SQLiteDatabase db = this.getReadableDatabase();

        // Only fetch required columns
        Cursor resultSet = db.rawQuery(
                "SELECT ID, EmployeeID, LoanAmount, LoanStatus FROM " + LOAN_TABLE_NAME + " WHERE EmployeeID = ?",
                new String[]{empID}
        );

        if (resultSet.moveToFirst()) {
            autoLoanID = resultSet.getInt(0); // ID
            employeeID = resultSet.getString(1); // Employee ID
            loanAmount = resultSet.getDouble(2); // Loan Amount
            loanStatus = resultSet.getString(3); // Loan Status
            found = true;
        }
        resultSet.close();
        return found;
    }


    // Getters and Setters for Employee Table

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpMInitial() {
        return empMInitial;
    }

    public void setEmpMInitial(String empMInitial) {
        this.empMInitial = empMInitial;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    public String getEmpDateHired() {
        return empDateHired;
    }

    public void setEmpDateHired(String empDateHired) {
        this.empDateHired = empDateHired;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }


    // Getters and Setters for Loan Table

    public int getAutoLoanID() {
        return autoLoanID;
    }

    public void setAutoLoanID(int autoLoanID) {
        this.autoLoanID = autoLoanID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public int getNumOfMosToPay() {
        return numOfMosToPay;
    }

    public void setNumOfMosToPay(int numOfMosToPay) {
        this.numOfMosToPay = numOfMosToPay;
    }

    public double getMonthlyAmortization() {
        return monthlyAmortization;
    }

    public void setMonthlyAmortization(double monthlyAmortization) {
        this.monthlyAmortization = monthlyAmortization;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getTotalAmountOfLoan() {
        return totalAmountOfLoan;
    }

    public void setTotalAmountOfLoan(double totalAmountOfLoan) {
        this.totalAmountOfLoan = totalAmountOfLoan;
    }

    public double getTakeHomeLoan() {
        return takeHomeLoan;
    }

    public void setTakeHomeLoan(double takeHomeLoan) {
        this.takeHomeLoan = takeHomeLoan;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
