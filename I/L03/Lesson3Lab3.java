/*
    Name: Stephen Gerkin
    Date: 01/18/2019
    Lesson 3 Lab 3
    Program Title:
        Decimal addition program
    Program Description:
        A program that prompts the user to input five decimal numbers.
        The program should then add the five decimal numbers, convert the
        sum to the nearest integer, and print the result.

*/
package lesson.pkg3.lab.pkg3;

import java.util.Scanner;


/**
 * Class Definition
 */
public class Lesson3Lab3 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);  // scanner var for input
        
        final long  numbersToEnter = 5;     // # of entries to get from user
        double      userInput = 0;          // var for userInput
        
        // use for loop to get numbers
        for (int i = 1; i <= numbersToEnter; i++) {
            System.out.println("Enter number " + i + " to add: ");
            userInput += keyboard.nextDouble();
        }
        
        // round the final number
        userInput = Math.round(userInput);
        
        // print the final rounded number
        System.out.println("The final number rounded to the nearest integer is " + (long)userInput);
        
    }
    
}
