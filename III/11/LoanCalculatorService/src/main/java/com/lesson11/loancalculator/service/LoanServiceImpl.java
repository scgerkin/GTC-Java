package com.lesson11.loancalculator.service;

import com.lesson11.loancalculator.service.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;

/**
 * Service class for calculating the payments on a loan.
 * The annotation tells Spring that this is the implementation of the the
 * LoanService interface we want to use in the controller.
 */
@Service
public class LoanServiceImpl implements LoanService {

    /**Fields for min/max values we will allow for calculation*/
    private final Double LOAN_MIN = 500.00;
    private final Integer YEAR_MIN = 1;
    private final Integer YEAR_MAX = 100;
    private final Double MIN_INTEREST = 0.0;
    private final Double MAX_INTEREST = 100.00;

    /**
     * Calculates the monthly minimum payment required to pay off a loan given
     * a loan amount, number of years to pay off the loan, and an interest rate.
     */
    @Override
    public Double monthlyPayment(Double amount, Integer numYears, Double interestRate) {
        if (validAmount(amount) && validYears(numYears) && validInterestRate(interestRate)) {
            return (amount * interestRate / (1 - (1 / Math.pow(1 + interestRate, numYears * 12))));
        } else {
            throw new InvalidRequestException();
        }
    }

    /**
     * Calculates the total payment required to pay off a loan, given the number
     * of years to pay off the loan and an interest rate. Uses the method to
     * calculate the monthly payment and calculates based on that.
     */
    @Override
    public Double totalPayment(Double amount, Integer numYears, Double interestRate) {
        return monthlyPayment(amount, numYears, interestRate) * numYears * 12;
    }

    /**
     * Validates that a loan amount is not null and at least the minimum loan
     * amount.
     */
    private boolean validAmount(Double amount) {
        return !(amount == null) && (amount.compareTo(LOAN_MIN) >= 0);
    }

    /**
     * Validates that a year entry is not null and at least the minimum number
     * of years allowed and at most the maximum number of years allowed.
     */
    private boolean validYears(Integer years) {
        return !(years == null) &&
                   (years.compareTo(YEAR_MIN) >= 0) &&
                   (years.compareTo(YEAR_MAX) <= 0);
    }

    /**
     * Validates that an interest rate is not null and greater than the minimum
     * interest rate allowed and less than the maximum interest rate allowed.
     */
    private boolean validInterestRate(Double interestRate) {
        return !(interestRate == null) &&
                   (interestRate.compareTo(MIN_INTEREST) > 0) &&
                   (interestRate.compareTo(MAX_INTEREST) < 0);
    }
}
