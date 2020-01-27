package bankcharges;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *  @author  Stephen Gerkin
 *  @date    2019-05-14
 *  Programming Lab 1 - 1. Bank Charges
 *  Gets number of checks written by a user
 *  Calculates total service fee to pay based on total checks written
 *  Displays result
 */
public class BankCharges {

    // class Scanner obj
    private static Scanner kb = new Scanner(System.in);

    /**
     * Main method wrangles program methods
     * @param args  command line args
     */
    public static void main(String[] args) {
        int checksWritten = 0;
        double checkFee = 0.00;
        double totalFee = 0.00;

        checksWritten = getNumChecks();

        checkFee = determineCheckFee(checksWritten);

        totalFee = calcTotalFee(checksWritten, checkFee);

        displayFee(totalFee);

        System.exit(0);
    }

    /**
     * Gets number of checks written by user
     * @return int number of checks written
     */
    public static int getNumChecks() {
        int numChecks = 0;

        // get number of checks written
        System.out.println("How many checks were written for the month?");
        numChecks = Integer.parseInt(kb.nextLine());

        // validate
        if (numChecks < 0) {
            System.out.println(
                "Invalid entry. Please enter a positive integer value."
            );
            return getNumChecks();
        }

        return numChecks;
    }

    /**
     * Determines the checking fee per number of checks written
     * @param numChecks Number of checks written
     * @return double   Check fee
     */
    public static double determineCheckFee(int numChecks) {
        if (numChecks < 20) {
            return 0.10;
        }

        if (numChecks < 40) {
            return 0.08;
        }

        if (numChecks < 60) {
            return 0.06;
        }
        // if function gets here, fee is 0.04
        return 0.04;
    }

    /**
     * Calculates the total fee charged
     * @param numChecks Number of checks written
     * @param checkFee  Fee per check written
     * @return double   Total fee for checks written
     */
    public static double calcTotalFee(int numChecks, double checkFee) {
        final double BASE_FEE = 10.00;

        return (numChecks * checkFee) + BASE_FEE;
    }

    /**
     * Displays total fee
     * @param fee   Total fee
     */
    public static void displayFee(double fee) {
        DecimalFormat dollars = new DecimalFormat("$###,##0.00");

        System.out.println("The total charge is: " + dollars.format(fee) + ".");
    }
}
