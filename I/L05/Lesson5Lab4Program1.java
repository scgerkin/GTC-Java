/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 5 Lab 4 Program 1
    Program Title:
        Software Sales
    Program Description:
        Program that asks user to enter a number of packages purchased
        Then displays appropriate discount amount and total amount of purchase

 */

package lesson.pkg5.lab.pkg4.program.pkg1;

import javax.swing.JOptionPane;
import java.text.NumberFormat;

/**
 * Class definition
 */
public class Lesson5Lab4Program1 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // local constant for price
        final double PRICE = 99.00;
        
        // currency format
        NumberFormat money = NumberFormat.getCurrencyInstance();
        
        // local variables
        int packagesPurchased;
        double discountRate,
               priceBeforeDiscount,
               discountAmount,
               finalPrice;
        
        // get packages purchased
        packagesPurchased = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of packages purchased."));

        // determine discount rate
        if (packagesPurchased >= 100) {
            discountRate = 0.50;
        }
        else if (packagesPurchased >= 50) {
            discountRate = 0.40;
        }
        else if (packagesPurchased >= 20) {
            discountRate = 0.30;
        }
        else if (packagesPurchased >= 10) {
            discountRate = 0.20;
        }
        else {
            discountRate = 0.00;
        }

        // calc discount amount and amount paid
        priceBeforeDiscount = PRICE*packagesPurchased;
        discountAmount = PRICE*packagesPurchased*discountRate;
        finalPrice = priceBeforeDiscount - discountAmount;

        // display discount rate, amount, and final price
        JOptionPane.showMessageDialog(null,
            "The discount for the purchase is " + discountRate*100 + "%.\n" +
            "The amount of discount received is " + money.format(discountAmount) + ".\n" +
            "The total amount of the purchase is " + money.format(finalPrice) + "."
        );
    }
}
