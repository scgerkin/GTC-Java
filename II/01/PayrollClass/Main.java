package payrollclass;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *  @author Stephen Gerkin
 *  @date 2019-05-15
 *  Programming Lab 1 - 2. Payroll Class
 *  Main class to demonstrate the Payroll Class
 */
public class Main {

    // class constants
    public static final int MIN_HRS = 0;
    public static final double MIN_PAY = 6.00;

    // class I/O objs
    private static Scanner kb = new Scanner(System.in);
    private static DecimalFormat dollar = new DecimalFormat("$###,###.00");

    /**
     * Main method demonstrates Payroll Class
     * @param args command line args
     */
    public static void main(String[] args) {
        // create new Payroll object
        Payroll demoPay = new Payroll();

        // populate the object arrays with user input values
        for (int i = 0; i < demoPay.NUM_EMPLOYEES; i++) {
            System.out.println("Getting information for employee ID: "
            + demoPay.employeeID[i] + "..."
        );

            demoPay.setHours(i, getHours());
            demoPay.setPayRate(i, getPayRate());
            demoPay.setWages(i);

            System.out.println("\n");
        }

        // print all wages for the object
        System.out.println("The gross pay for each employee is:");
        for (int i = 0; i < demoPay.NUM_EMPLOYEES; i++) {
            System.out.println(
                "ID: " + demoPay.employeeID[i] + ": "
                + dollar.format(demoPay.getWages(demoPay.employeeID[i]))
            );
        }

        System.out.println("\n");


        while (true) {
            getSpecificWage(demoPay);
            System.out.println("\n");

            if (!continueInput()) {
                break;
            };

            System.out.println("\n");
        }

        System.out.println("Program terminating...");
        System.exit(0);
    }

    /**
     * function to get hour input from user and validate input
     * @return valid input as integer
     */
    public static int getHours() {
        System.out.println("Enter the number of hours worked: ");
        int returnVal = Integer.parseInt(kb.nextLine());

        // if invalid input, recur
        if (returnVal < MIN_HRS) {
            System.out.println(
                "Invalid input. Hours must be a positive value."
            );
            return getHours();
        }

        // return input
        return returnVal;
    }

    /**
     * function to get pay rate from user and validate input
     * @return valid input as double
     */
    public static double getPayRate() {
        // get input
        System.out.println("Enter the the pay rate:");
        double returnVal = Double.parseDouble(kb.nextLine());

        // validate entry, if invalid recur
        if (returnVal < MIN_PAY) {
            System.out.println(
                "Invalid input. Pay rate must be at least "
                + dollar.format(MIN_PAY)
            );
            return getPayRate();
        }

        return returnVal;
    }

    /**
     * function to get wages for a specific employee ID, prints wages if found
     * prints error message if not found
     * @param demo Payroll object to use
     */
    public static void getSpecificWage(Payroll demo) {
        System.out.println(
            "Enter the Employee ID you would like to get the wages for:"
        );
        int id = Integer.parseInt(kb.nextLine());

        if (demo.getWages(id) < 0) {
            System.out.println("That ID was not found.");
        }
        else {
            System.out.println(
                "Wages for that employee ID are: "
                + dollar.format(demo.getWages(id))
            );
        }
    }

    /**
     * function to check if user wants to enter more IDs to search for
     * @return user choice, Y = true, N = False
     */
    public static boolean continueInput() {
        // get input as upper
        System.out.println("Do you want to get input for more IDs? (Y/N):");
        char userInput = Character.toUpperCase(kb.nextLine().charAt(0));

        // validate entry, if invalid recur
        if (userInput != 'Y' && userInput != 'N') {
            System.out.println("Invalid input.");
            return continueInput();
        }

        return (userInput == 'Y') ? true : false;
    }
}
