/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 4 Lab 3 Program 1
    Program Title:
        Restaurant Bill
    Program Description:
        A program that prompts the user to enter the cost of a meal
        Then displays tax amount, tip amount, and total charge for meal

*/
package lesson.pkg4.lab.pkg3.program.pkg1;

import javax.swing.JOptionPane;
import java.text.NumberFormat;

/**
 * Class definition 
 */
public class Lesson4Lab3Program1 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // set number format as currency
        NumberFormat money = NumberFormat.getCurrencyInstance();
        
        // local constants
        final double TAX_RATE = 0.0675,
                     TIP_RATE = 0.20;
        
        // local variables
        double mealCharge,
               taxAmount,
               tipAmount;
        
        // get cost of meal
        mealCharge = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount charged for the meal."));
        
        // calc tax and tip amount
        taxAmount = mealCharge * TAX_RATE;
        tipAmount = (mealCharge + taxAmount) * TIP_RATE;
        
        // display tax amount, tip amount, and total charge
        JOptionPane.showMessageDialog(
            null,
            "The amount charged for your meal was " + money.format(mealCharge) + ".\n" +
            "The total tax for the meal is " + money.format(taxAmount) + ".\n" +
            "The total tip you should leave is " + money.format(tipAmount) + ".\n" +
            "The total check is " + money.format((mealCharge + taxAmount + tipAmount)) + "."
        );
    }
}
