/*
 * SavingsAccount class definition
 */

package lesson10lab9program1;

// class dependencies
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SavingsAccount class creates an object for calculating the monthly values
 * of a savings account.
 */
public class SavingsAccount {
   
    // variables for object
    private final double monthlyInterestRate;
    private double totalBalance;
    private double totalDeposits;
    private double totalWithdrawn;
    private double totalInterestEarned;
        
    /**
     * constructor to create an object with returned values
     * @param annualInterestRate annual interest rate as a percentage
     * @param startBalance starting balance of the account
     */
    public SavingsAccount(double annualInterestRate, double startBalance) {
        monthlyInterestRate = (annualInterestRate / 100) / 12;
        totalBalance = startBalance;
        totalDeposits = 0;
        totalWithdrawn = 0;
        totalInterestEarned = 0;
    }
    
    /**
     * Helper method using Big Decimal to round interest amount values to 2
     * decimal places.
     * @param num value to be rounded
     * @return value rounded to 2 decimal places
    */
    private static double roundMoney(double num) {
        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * getter to get total balance
     * @return total balance
     */
    public double getBalance() {
        return totalBalance;
    }
    
    /**
     * getter to get total deposited
     * @return total deposited
     */
    public double getDeposits() {
        return totalDeposits;
    }
    
    /**
     * getter to get total withdrawn
     * @return total withdrawn
     */
    public double getWithrawn() {
        return totalWithdrawn;
    }
    
    /**
     * getter to get total interest amount earned
     * @return total interest earned
     */
    public double getInterestEarned() {
        return totalInterestEarned;
    }
            
    /**
     * mutator for depositing money to account
     * @param deposit amount to deposit
     */
    public void deposit(double deposit) {
        totalBalance += deposit;
        totalDeposits += deposit;
    }
    
    /**
     * mutator for withdrawing money from account
     * @param withdraw amount to withdraw
     */
    public void withdraw(double withdraw) {
        totalBalance -= withdraw;
        totalWithdrawn += withdraw;
    }
    
    /**
     * mutator for calculating monthly interest to add to account
     */
    public void calcInterest() {
        double interest;    // temp var for interest calculations
        
        // only calc interest if balance is above 0
        if (totalBalance > 0); {
        
            // round interest before adding to balance
            interest = roundMoney(totalBalance * monthlyInterestRate);
        
            // add interest to balance and total interest earned
            totalBalance += interest;
            totalInterestEarned += interest;
        }
    }
}
