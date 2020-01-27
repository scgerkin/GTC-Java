package bankaccountandsavingsaccount;

import java.text.DecimalFormat;

/**
 * BankAccount abstract method holds information about a bank account and
 * provides methods for handling the account
 */
public abstract class BankAccount {
    // Decimal Format objects for displaying $ and %
    protected static final DecimalFormat df = new DecimalFormat("$###,##0.00");
    private static final DecimalFormat pc = new DecimalFormat("##0.00%");

    // protected member variables used by class, protected to allow subclass
    // access
    protected double balance;
    protected int numDeposits;
    protected int numWithdrawals;
    protected double interestRate;
    protected double serviceCharge;

    /**
     * Default constructor
     * @param startingBalance
     * @param annualInterestRate
     */
    public BankAccount(double startingBalance, double annualInterestRate) {
        this.balance = startingBalance;
        this.interestRate = annualInterestRate;
        this.numDeposits = 0;
        this.numWithdrawals = 0;
        this.serviceCharge = 0;
    }

    /**
     * Method to deposit amount to balance
     * @param depositAmount
     */
    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        this.numDeposits++;
    }

    /**
     * Method to withdraw amount from balance
     * @param withdrawalAmout
     */
    public void withdraw(double withdrawalAmout) {
        this.balance -= withdrawalAmout;
        this.numWithdrawals++;
    }

    /**
     * Method to add interest to balance
     */
    public void addInterest() {
        this.balance += (this.interestRate / 12) * this.balance;
    }

    /**
     * Method for calculating monthly account actions
     */
    public void monthlyProcess() {
        this.balance -= this.serviceCharge;

        addInterest();

        this.numDeposits = 0;
        this.numWithdrawals = 0;
        this.serviceCharge = 0;
    }

    /**
     * Abstract method for processing monthly service charges
     */
    protected abstract void monthlyServiceCharge();

    /**
     * toString override for class demonstration purposes
     */
    @Override
    public String toString() {
        return "Balance: " + df.format(this.balance) + "\n"
               + "Interest Rate: " + pc.format(this.interestRate) + "\n"
               + "Deposits: " + this.numDeposits + "\n"
               + "Withdrawals: " + this.numWithdrawals + "\n"
               + "Service Charge: " + df.format(this.serviceCharge);
    }
}
