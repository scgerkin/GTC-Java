/*
Name: Stephen Gerkin
Date: 03/25/2019
Lesson 11 Lab 10 Program 1
Program Title:
    Payroll Class
Program Description:
    This program creates a class called Payroll with multiple parallel arrays
    and demonstrates the class methods
 */
package lesson11lab10;

// program dependencies
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *  This program demonstrates the Payroll class
 * @author sgerkin
 */
public class Lesson11Lab10 {

    // global scanner, string, and decimal format objects
    public static Scanner kb = new Scanner(System.in);
    public static String outMsg;
    public static DecimalFormat dollar = new DecimalFormat("$###,###.00");
    
    // global constants
    public static final int MIN_HRS = 0;
    public static final double MIN_PAY = 6.00;
    
    
    /**
     * Main method definition
     * @param args
     */
    public static void main(String[] args) {
        // create new Payroll object
        Payroll demoPay = new Payroll();
        
        // populate the object arrays with user input values
        for (int i = 0; i < demoPay.NUM_EMPLOYEES; i++) {
            outMsg = "Getting information for employee ID: "
                    + demoPay.employeeID[i] + "...";
            System.out.println(outMsg);
            
            demoPay.setHours(i, getHours());
            demoPay.setPayRate(i, getPayRate());
            demoPay.setWages(i);
            
            System.out.println("\n");
        }

        // print all wages for the object
        outMsg = "The gross pay for each employee is:";
        System.out.println(outMsg);        
        for (int i = 0; i < demoPay.NUM_EMPLOYEES; i++) {
            outMsg = "ID: " + demoPay.employeeID[i] + ": "
                    + dollar.format(demoPay.getWages(demoPay.employeeID[i]));
            System.out.println(outMsg);
        }
        
        System.out.println("\n");
        
        // bool var for checking if user wants to enter more IDs
        boolean getMoreInput = true;
        
        while (getMoreInput) {
            getSpecificWage(demoPay);
            System.out.println("\n");
            getMoreInput = queryGetMore();
            System.out.println("\n");
        }
        
        outMsg = "Program terminating...";
        System.out.println(outMsg);
        
        // terminate JVM with success
        System.exit(0);
    }
    
    /**
     * function to get hour input from user and validate input
     * @return valid input as integer
     */
    public static int getHours() {
        outMsg = "Enter the number of hours worked: ";
        System.out.println(outMsg);
        int returnVal = Integer.parseInt(kb.nextLine());
        
        // if invalid input, recur
        if (returnVal < MIN_HRS) {
            outMsg = "Invalid input. Hours must be a positive value.";
            System.out.println(outMsg);
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
        outMsg = "Enter the the pay rate: $";
        System.out.println(outMsg);
        double returnVal = Double.parseDouble(kb.nextLine());
        
        // if invalid input, recur
        if (returnVal < MIN_PAY) {
            outMsg = "Invalid input. Pay rate must be at least $6.00";
            System.out.println(outMsg);
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
        int id;
        
        outMsg = "Enter the Employee ID you "
               + "would like to get the wages for:";
        System.out.println(outMsg);
        
        id = Integer.parseInt(kb.nextLine());
        
        if (demo.getWages(id) < 0) {
            outMsg = "That ID was not found.";
            System.out.println(outMsg);
        }
        else {
            outMsg = "Wages for that employee ID are: "
                    + dollar.format(demo.getWages(id));
            System.out.println(outMsg);
        }
    }
    
    /**
     * function to check if user wants to enter more IDs to search for
     * @return user choice, Y = true, N = False
     */
    public static boolean queryGetMore() {
        char userInput;
        
        outMsg = "Do you want to get input for more IDs? (y/n):";
        System.out.println(outMsg);
        
        // get first letter of input as char, convert to upper
        userInput = Character.toUpperCase(kb.nextLine().charAt(0));
        
        // if invalid input, recur
        if (userInput != 'Y' && userInput != 'N') {
            outMsg = "Invalid input.";
            System.out.println(outMsg);
            return queryGetMore();
        }
        
        // return true if input Y, o/w return false
        return (userInput == 'Y');
    }
}
