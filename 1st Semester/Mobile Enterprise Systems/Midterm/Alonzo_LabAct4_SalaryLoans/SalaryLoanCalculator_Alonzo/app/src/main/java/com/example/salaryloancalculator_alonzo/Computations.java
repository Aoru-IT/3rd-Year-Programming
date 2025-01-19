package com.example.salaryloancalculator_alonzo;

import java.util.ArrayList;

public class Computations {
    private static Computations instance = null;

    private ArrayList<String> firstNames;
    private ArrayList<String> lastNames;
    private ArrayList<Double> monthlySalaries;
    private ArrayList<String> emailAddresses;
    private ArrayList<String> passwords;

    private ArrayList<Double> loanableAmounts;
    private ArrayList<Double> interestRates;
    private ArrayList<Double> interests;
    private ArrayList<Double> serviceCharges;
    private ArrayList<Double> takeHomeLoans;
    private ArrayList<Double> monthlyAmortizations;


    private Computations() {
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        monthlySalaries = new ArrayList<>();
        emailAddresses = new ArrayList<>();
        passwords = new ArrayList<>();

        loanableAmounts = new ArrayList<>();
        interestRates = new ArrayList<>();
        interests = new ArrayList<>();
        serviceCharges = new ArrayList<>();
        takeHomeLoans = new ArrayList<>();
        monthlyAmortizations = new ArrayList<>();
    }

    public static Computations getInstance() {
        if (instance == null) {
            instance = new Computations();
        }
        return instance;
    }

    public String getFullName(int index) {
        return firstNames.get(index) + " " + lastNames.get(index);
    }

    public String getEmail(int index) {
        return emailAddresses.get(index);
    }

    public double getBasicMonthlySalary(int index) {
        return monthlySalaries.get(index);
    }

    public int getUserIndexByEmail(String email) {
        return emailAddresses.indexOf(email);
    }

    public double getLoanAmount(int userIndex){
        return loanableAmounts.get(userIndex);
    }

    public double getInterestRate(int userIndex){
        return interestRates.get(userIndex);
    }

    public double getInterest(int userIndex){
        return interests.get(userIndex);
    }

    public double getServiceCharge(int userIndex){
        return serviceCharges.get(userIndex);
    }

    public double getTakeHomeLoan(int userIndex){
        return takeHomeLoans.get(userIndex);
    }

    public double getMonthlyAmortization(int userIndex){
        return monthlyAmortizations.get(userIndex);
    }

    public void addRegistration(String firstName, String lastName, double salary, String email, String password) {
        firstNames.add(firstName);
        lastNames.add(lastName);
        monthlySalaries.add(salary);
        emailAddresses.add(email);
        passwords.add(password);

        loanableAmounts.add(salary * 2.5);
        interestRates.add(0.0);
        interests.add(0.0);
        serviceCharges.add(0.0);
        takeHomeLoans.add(0.0);
        monthlyAmortizations.add(0.0);
    }

    public boolean emailExists(String email) {
        return emailAddresses.contains(email);
    }

    public int login(String email, String password) {
        int index = emailAddresses.indexOf(email);
        if (index == -1) {
            return 1;
        }

        if (!passwords.get(index).equals(password)) {
            return 2;
        }

        return 0;
    }

    public void activityComputation(int userIndex, double loanAmount, int numberOfMonths){
        double interestRate = computeInterestRate(numberOfMonths);
        double interest = loanAmount * numberOfMonths * interestRate;

        double serviceCharge = loanAmount * 0.02;
        double takeHomeLoan = loanAmount - (interest + serviceCharge);
        double monthlyAmortization = loanAmount / numberOfMonths;

        interestRates.set(userIndex, interestRate);
        interests.set(userIndex, interest);
        serviceCharges.set(userIndex, serviceCharge);
        takeHomeLoans.set(userIndex, takeHomeLoan);
        monthlyAmortizations.set(userIndex, monthlyAmortization);
    }

    public double computeInterestRate(double numberOfMonths){
        if(numberOfMonths>=1 && numberOfMonths <=5){
            return 0.0062;
        }
        else if(numberOfMonths>=6 && numberOfMonths<=10){
            return 0.0065;
        }
        else if(numberOfMonths>=11 && numberOfMonths<=15){
            return 0.0068;
        }
        else if(numberOfMonths>=16 && numberOfMonths<=20){
            return 0.0075;
        }
        else if(numberOfMonths>=21 && numberOfMonths<=25){
            return 0.0080;
        }
        else{
            return 0;
        }
    }

}
