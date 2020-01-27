/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 4 Classwork Program 1
    Program Title:
        Stock Transaction Program
    Program Description:
        Program that calculates and money spent/earned on stock purchases
        Displays total amount spent/earned
        Determines if profit or loss and displays result

*/
package lesson.pkg4.classwork.program.pkg1;

import java.text.NumberFormat;

/**
 * Class Definition
 */
public class Lesson4ClassworkProgram1 {
    /**
     * Main method
     */
    public static void main(String[] args) {
        
        NumberFormat money = NumberFormat.getCurrencyInstance();
        
        final double COM_RATE = 0.02;
        
        int shares = 1000;
        
        double stockPrice1 = 32.87,
               stockPrice2 = 33.92,
               moneyOut,
               moneyIn;
        
        moneyOut = shares * stockPrice1;
        
        System.out.println("Joe purchased the stock for a total of " + money.format(moneyOut) + ".");
        System.out.println("He paid a commission of " + money.format(moneyOut * COM_RATE) + " on this purchase.");
        
        moneyOut += moneyOut * COM_RATE;
        
        System.out.println("The total amount paid on this purchase was " + money.format(moneyOut) + ".\n");
        
        moneyIn = shares * stockPrice2;
        
        System.out.println("Joe sold the stock for a total of " + money.format(moneyIn) + ".");
        System.out.println("He paid a commission of " + money.format(moneyIn * COM_RATE) + " on this sale.");
        
        moneyIn -= moneyIn * COM_RATE;
        
        System.out.println("The total amount earned on this sale was " + money.format(moneyIn) + ".\n");

        if (moneyOut > moneyIn) {
            System.out.println("Joe lost a total of " + money.format(moneyOut - moneyIn) + ".");
        }
        else {
            System.out.println("Joe earned a total of " + money.format(moneyIn - moneyOut) + ".");
        }
    }
}