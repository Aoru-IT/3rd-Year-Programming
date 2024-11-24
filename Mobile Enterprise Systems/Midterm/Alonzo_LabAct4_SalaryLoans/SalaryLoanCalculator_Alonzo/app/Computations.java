package com.example.salaryloancalculator_alonzo;

import java.util.ArrayList;

public class Computations{

    public double loanAmount, interestRate, interest,
            takeHomeLoan, serviceCharge, monthlyAmortization;

    private String firstName;
    private String lastName;
    private double basicMonthlySalary;
    private String emailAddress;
    private String password;

    private ArrayList<String> firstNames;
    private ArrayList<String> lastNames;
    private ArrayList<Double> monthlySalaries;
    private ArrayList<String> emailAddresses;
    private ArrayList<String> passwords;

    public Computations() {
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        monthlySalaries = new ArrayList<>();
        emailAddresses = new ArrayList<>();
        passwords = new ArrayList<>();
    }

    public void addRegistration(String firstName, String lastName, double salary, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.basicMonthlySalary = salary;
        this.emailAddress = email;
        this.password = password;

        firstNames.add(firstName);
        lastNames.add(lastName);
        monthlySalaries.add(salary);
        emailAddresses.add(email);
        passwords.add(password);
    }

    public String login(String email, String password) {
        int index = emailAddresses.indexOf(email);
        if (index == -1) {
            return "Error: No account found with this email.";
        }

        if (!passwords.get(index).equals(password)) {
            return "Error: Incorrect password.";
        }

        return "Login successful!";
    }

    loanAmount = basicMonthlySalary * 2.5;
    public void activityComputation(double basicMonthlySalary, int numberOfMonths){
    interestRate = computeInterestRate(numberOfMonths);
    interest = loanAmount * numberOfMonths;

    serviceCharge = loanAmount * 0.02;
    takeHomeLoan = loanAmount - (interest + serviceCharge);
    monthlyAmortization = loanAmount / numberOfMonths;
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
            return 0.075;
        }
        else if(numberOfMonths>=21 && numberOfMonths<=25){
            return 0.080;
        }
        else{
            return 0;
        }
     }
}
