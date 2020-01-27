package bankaccountandsavingsaccount;

public class SavingsAccount extends BankAccount {
    // minimum amount allowed to maintain an active balance
    private final double MIN_BALANCE = 25.00;

    private Boolean active;

    /**
     * Default contstructor calls super and verifies account is active
     * @param balance
     * @param interestRate
     */
    public SavingsAccount(double balance, double interestRate) {
        super(balance, interestRate);

        checkActive();
    }

    /**
     * Method to verify account is currently active
     */
    private void checkActive() {
        this.active = !(this.balance < MIN_BALANCE);
    }

    /**
     * Class override of withdraw() to verify account is active
     */
    @Override
    public void withdraw(double withdrawalAmount) {
        if (this.active) {
            super.withdraw(withdrawalAmount);
        }
        else {
            System.out.println(
                "This account is inactive and must be raised above "
                + df.format(MIN_BALANCE)
                + " before any more withdrawals can be made."
                + "\n"
            );
        }
        checkActive();
    }

    /**
     * Class override of deposit() to verify account is active
     */
    @Override
    public void deposit(double depositAmount) {
        super.deposit(depositAmount);
        checkActive();
    }

    /**
     * Provides class specific methods for monthlyProcess and calls superclass
     * monthlyProcess
     */
    @Override
    public void monthlyProcess() {
        monthlyServiceCharge();
        super.monthlyProcess();
        checkActive();
    }

    /**
     * Updates account monthly service charges on account
     */
    @Override
    protected void monthlyServiceCharge() {
        if (this.numWithdrawals > 4) {
            this.serviceCharge = 1.00 * (this.numWithdrawals - 4);
        }
    }

    /**
     * toString override for class demonstration purposes
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + ((this.active) ? "Active" : "Inactive");
    }
}
