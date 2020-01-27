/*
    Name: Stephen Gerkin
    Date: 01/18/2019
    Lesson 3 Lab 1
    Program Title:
        Round Decimals
    Program Description:
        A program that prompts the user to input a decimal number and outputs
        the number rounded to the nearest integer.

*/
package lesson.pkg3.lab.pkg1;

import java.util.Scanner;

/**
 * Class Definition
 */
public class Lesson3Lab1 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        
        double userVal;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter a number containing a decimal ");
        userVal = keyboard.nextDouble();
        
        System.out.println("You entered " + userVal +
                "\n Rounded to the nearest integer that is " +
                Math.round(userVal));
    }
    
}
