package com.lesson11.loancalculator.entity;

/**
 * Loan class encapsulates the information needed to calculate a loan payment
 * and also holds the result.
 * This is a POJO for demonstrating a REST service and a client that consumes it.
 */
public class Loan {
    private Double loanAmount;
    private Integer numYears;
    private Double interestRate;
    private Double result;

    public Loan() {}

    public Loan(Double loanAmount, Integer numYears, Double interestRate, Double result) {
        this.loanAmount = loanAmount;
        this.numYears = numYears;
        this.interestRate = interestRate;
        this.result = result;
    }

    public Loan(Double loanAmount, Integer numYears, Double interestRate) {
        this.loanAmount = loanAmount;
        this.numYears = numYears;
        this.interestRate = interestRate;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getNumYears() {
        return numYears;
    }

    public void setNumYears(Integer numYears) {
        this.numYears = numYears;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
