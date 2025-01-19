package com.example.acs_loanapp;

public class Computations {


    // Emergency Loan
    public double EmergencyLoan_ServiceCharge(double loanAmount) {
        return loanAmount * 0.01;
    }

    public double EmergencyLoan_InterestRate(double loanAmount, int numOfMosToPay) {
        if (numOfMosToPay >= 1 && numOfMosToPay <= 6) {
            return 0.0060;
        }
        return 0.0;
    }

    public double EmergencyLoan_LoanInterest(double loanAmount, int numOfMosToPay) {
        return (numOfMosToPay * 0.0060) * loanAmount;
    }

    public double EmergencyLoan_Cash(double loanAmount, double serviceCharge) {
        return loanAmount + serviceCharge;
    }

    public double EmergencyLoan_PayableAmount(double loanAmount, double serviceCharge, double loanInterest, int numOfMosToPay) {
        if (numOfMosToPay == 0) {
            return 0;
        }
        return (loanAmount + serviceCharge + loanInterest) / numOfMosToPay;
    }

    public double EmergencyLoan_TakeHomeLoan(double loanAmount, double serviceCharge, double loanInterest) {
        return loanAmount - (loanInterest + serviceCharge);
    }

    public double EmergencyLoan_MonthlyAmortization(double takeHomeLoan, int numOfMosToPay) {
        if (numOfMosToPay == 0) {
            return 0;
        }
        return takeHomeLoan / numOfMosToPay;
    }

    // Special Loan
    public double SpecialLoan_InterestRate(double MonthsToPay) {
        if (MonthsToPay >= 1 && MonthsToPay <= 6) {
            return 0.0060;
        }
        if (MonthsToPay >= 7 && MonthsToPay <= 12) {
            return 0.0062;
        }
        if (MonthsToPay >= 13 && MonthsToPay <= 18) {
            return 0.0065;
        }
        return 0;
    }

    public double SpecialLoan_LoanAmount(double LoanAmount) {
        return LoanAmount;
    }

    public double SpecialLoan_MonthsToPay(double Month) {
        return Month;
    }

    public double SpecialLoan_LoanInterest(double LoanAmount, double MonthsToPay, double InterestRate) {
        return LoanAmount * MonthsToPay * InterestRate;
    }

    public double SpecialLoan_TotalAmount(double LoanAmount, double Interest) {
        return LoanAmount + Interest;
    }

    public double SpecialLoan_MonthlyAmortization(double TotalAmount, double MonthsToPay) {
        return TotalAmount / MonthsToPay;
    }

    public double SpecialLoan_ServiceCharge(double LoanAmount) {
        return LoanAmount * 0.02;
    }

    public double SpecialLoan_TakeHomeLoan(double LoanAmount, double ServiceCharge, double LoanInterest) {
        return LoanAmount - (LoanInterest + ServiceCharge);
    }

    // Regular Loan
    public double RegularLoan_LoanableAmount(double Salary) {
        return Salary * 2.5;
    }

    public double RegularLoan_InterestRate(double MonthsToPay) {
        if (MonthsToPay >= 1 && MonthsToPay <= 5) {
            return 0.0062;
        }
        if (MonthsToPay >= 6 && MonthsToPay <= 10) {
            return 0.0065;
        }
        if (MonthsToPay >= 11 && MonthsToPay <= 15) {
            return 0.0068;
        }
        if (MonthsToPay >= 16 && MonthsToPay <= 20) {
            return 0.0075;
        }
        if (MonthsToPay >= 21 && MonthsToPay <= 24) {
            return 0.0080;
        }
        return 0;
    }

    public double RegularLoan_LoanInterest(double LoanAmount, double MonthsToPay, double InterestRate) {
        return LoanAmount * MonthsToPay * InterestRate;
    }

    public double RegularLoan_ServiceCharge(double LoanAmount) {
        return LoanAmount * 0.02;
    }

    public double RegularLoan_TakeHomeLoan(double LoanAmount, double ServiceCharge, double LoanInterest) {
        return LoanAmount - (LoanInterest + ServiceCharge);
    }

    public double RegularLoan_MonthlyAmortization(double TakeHomeLoan, double MonthsToPay) {
        return TakeHomeLoan / MonthsToPay;
    }
}
