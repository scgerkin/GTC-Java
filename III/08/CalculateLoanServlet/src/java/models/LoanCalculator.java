package models;

public class LoanCalculator {
    private double loanAmount;
    private double annualInterestRate;
    private int numberOfYears;
    private double monthlyPayment;
    private double totalPayment;

    public LoanCalculator(double loanAmount, double annualInterestRate, int numberOfYears) {
        this.loanAmount = loanAmount;
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
    }
    
    private void calcLoanAmounts() {
        double monthlyInterestRate = annualInterestRate / 1200;
        monthlyPayment = loanAmount * monthlyInterestRate /
                             (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
        totalPayment = monthlyPayment * numberOfYears * 12;
    }
    
    public double getMonthlyPayment() {
        calcLoanAmounts();
        return monthlyPayment;
    }
    
    public double getTotalPayment() {
        calcLoanAmounts();
        return totalPayment;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

}
