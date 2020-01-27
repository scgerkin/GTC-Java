package com.lesson11.loancalculator.service;

import com.lesson11.loancalculator.entity.Loan;

/**
 * An interface for calculating a loan payment.
 */
public interface LoanService {
    Double monthlyPayment(Double amount, Integer numYears, Double interestRate);
    Double totalPayment(Double amount, Integer numYears, Double interestRate);
}
