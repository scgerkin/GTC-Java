/*
Name: Stephen Gerkin
Date: 03/20/2019
Lesson 10 Lab 9 Program 1
Program Title:
    SavingsAccount class
Program Description:
    This program creates a class called SavingsAccount and demonstrates the
    methods of the class by creating an object and then changing the values
    of the object with a for loop.
 */
package lesson10lab9program1;

import java.util.Scanner;
import java.text.NumberFormat;


/**
 * This program demonstrates the SavingsAccount class
 */
public class Lesson10Lab9Program1 {

    // global scanner object for input
    public static Scanner kb = new Scanner(System.in);
    
    // global NumberFormat object for formatting output as currency
    public static NumberFormat money = NumberFormat.getCurrencyInstance();
    
    /**
     * Main method definition
     * @param args
     */
    public static void main(String[] args) {
        String outputMsg;
        double startingBalance;
        double annualInterestRate;
        int monthsEstablished;
        
        // get annual interest rate
        outputMsg = "Enter the annual interest rate for the account"
                + " as a percentage:";
        System.out.println(outputMsg);
        annualInterestRate = Double.parseDouble(kb.nextLine());

        // get starting balance
        outputMsg = "Enter the starting balance of the account:";
        System.out.println(outputMsg);
        startingBalance = Double.parseDouble(kb.nextLine());

        // create new SavingsAccount object with returned values
        SavingsAccount demoAccount =
                new SavingsAccount(annualInterestRate, startingBalance);
        
        // get # months account active
        outputMsg = "How many months have passed since the account was "
                + "established?";
        System.out.println(outputMsg);
        monthsEstablished = Integer.parseInt(kb.nextLine());
        
        // for loop for each month account was active
        for (int i = 1; i <= monthsEstablished; i++) {
            // get deposit for month
            outputMsg = "Enter the amount deposited for month " + i + ":";
            System.out.println(outputMsg);
            demoAccount.deposit(Double.parseDouble(kb.nextLine()));
            
            // get withdrawn amount for month
            outputMsg = "Enter the amount withdrawn for month " + i + ":";
            System.out.println(outputMsg);
            demoAccount.withdraw(Double.parseDouble(kb.nextLine()));
            
            // calculate interest
            demoAccount.calcInterest();
        }
        
        
        // display ending balance
        outputMsg = "The total ending balance of the account is "
                + money.format(demoAccount.getBalance());
        System.out.println(outputMsg);
        
        // display total amount of deposits
        outputMsg = "The total amount of deposits is "
                + money.format(demoAccount.getDeposits());
        System.out.println(outputMsg);
        
        // display total amount of withdrawals
        outputMsg = "The total amount of withdrawals is "
                + money.format(demoAccount.getWithrawn());
        System.out.println(outputMsg);
        
        // display total amount of interest earned
        outputMsg = "The total amount of interest earned is "
                + money.format(demoAccount.getInterestEarned());
        System.out.println(outputMsg);
        
        // terminate JVM with success
        System.exit(0);
    }
}
