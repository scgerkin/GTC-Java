package loancalculator;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Loan class holds information about a loan including the annual interest rate,
 * loan amount, number of years to pay off the loan. Provides methods to calculate
 * and provide the minimum monthly payment to pay off the loan on time and the
 * total amount of payment if only paying the minimum monthly payment each month.
 * Provides getters and setters for all member variables as well as methods to
 * retrieve String representations of currencies and dates formatted based on the
 * user default locale detected at instantiation.
 */
@Named(value = "loan")
@ApplicationScoped
public class Loan {
    // for storing loan information
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private double monthlyPayment;
    private double totalPayment;
    private java.util.Date loanDate = new Date();

    // for formatting String representations of loan information
    private Locale locale = Locale.getDefault();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,locale);

    /**No-arg constructor sets default values for member variables.
     * annualInterestRate = 2.5%
     * numberOfYears = 1
     * loanAmount = 1000
     * */
    public Loan() {
        this(2.5, 1, 1000);
    }

    /**
     * Default constructor.
     * @param annualInterestRate annual interest rate as percentage (eg 2.5, not 0.025).
     * @param numberOfYears number of years before the loan must be fully repaid.
     * @param loanAmount amount of the loan.
     */
    public Loan(double annualInterestRate, int numberOfYears,
                double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
    }

    /**
     * Getter for annualInterestRate.
     * @return annualInterestRate.
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * Setter for annualInterestRate.
     * @param annualInterestRate annual interest rate as percentage (eg 2.5, not 0.025).
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Getter for numberOfYears.
     * @return number of years before the loan must be fully repaid.
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * Setter for numberOfYears.
     * @param numberOfYears number of years before the loan must be fully repaid.
     */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * Getter for loanAmount.
     * @return amount of the loan.
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * Getter for loanAmount Formatted String.
     * @return amount of the loan formatted for currency in the user's default locale.
     */
    public String getLoanAmountString() {
        return currencyFormat.format(getLoanAmount());
    }

    /**
     * Setter for loanAmount.
     * @param loanAmount amount of the loan.
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Calculates the monthly payment based on loan information.
     */
    private void calcMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        monthlyPayment = loanAmount * monthlyInterestRate /
                             (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    }

    /**
     * Getter for monthly payment.
     * @return monthly payment required to pay off the loan.
     */
    public double getMonthlyPayment() {
        calcMonthlyPayment();
        return monthlyPayment;
    }

    /**
     * Getter for monthlyPayment Formatted String.
     * @return monthly payment required to pay off the loan formatted for currency
     * in the user's default locale.
     */
    public String getMonthlyPaymentString() {
        return currencyFormat.format(getMonthlyPayment());
    }

    /**
     * Calculates the total payment based on loan information.
     */
    private void calcTotalPayment() {
        totalPayment = getMonthlyPayment() * numberOfYears * 12;
    }

    /**
     * Getter for totalPayment.
     * @return total payment required to pay off the loan in full.
     */
    public double getTotalPayment() {
        calcTotalPayment();
        return totalPayment;
    }

    /**
     * Getter for totalPayment Formatted String.
     * @return total payment required to pay off the loan in full formatted for
     * currency in the user's default locale.
     */
    public String getTotalPaymentString() {
        return currencyFormat.format(getTotalPayment());
    }

    /**
     * Getter for loanDate.
     * @return the date the loan was taken out.
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * Getter for laonDate Formatted String.
     * @return the date the loan was taken out formatted for display in user's
     * default locale.
     */
    public String getLoanDateString() {
        return dateFormat.format(loanDate);
    }

    /**
     * Setter for loan start date.
     * @param loanStartDate The date the loan is to start.
     */
    public void setDate(Date loanStartDate) {
        this.loanDate = loanStartDate;
    }

    /**
     * Checks to make sure all fields are greater than 0
     * @return True if all fields are valid
     */
    private boolean isValidFields() {
        return annualInterestRate > 0 && numberOfYears > 0 && loanAmount > 0;
    }

    /**
     * For processing loan on facelet
     * @return If all fields are valid, navigate to the results page.
     */
    public String processSubmit() {
        return (isValidFields()) ? "LoanResults" : "";
    }

    /**
     * Resets fields to 0 and relaunches LoanCalculator page
     * */
    public String resetFields() {
        annualInterestRate = 0;
        numberOfYears = 0;
        loanAmount = 0;
        monthlyPayment = 0;
        totalPayment = 0;
        return "LoanCalculator";
    }
}