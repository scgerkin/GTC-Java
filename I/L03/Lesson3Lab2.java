/*
    Name: Stephen Gerkin
    Date: 01/18/2019
    Lesson 3 Lab 2
    Program Title:
        Test Score Calculator
    Program Description:
        A program that prompts the user to enter five test scores and
        then prints the average test score.

*/
package lesson.pkg3.lab.pkg2;

import java.util.Scanner;

/**
 * Class Definition
 */
public class Lesson3Lab2 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); // scanner var for input
        
        double      testScore = 0;      // variable for test scores
        final int   testsToScore = 5;   // total # of tests to get
        
        
        // use for loop to get test scores
        for (int i = 1; i <= testsToScore; i++) {
            System.out.println("Enter the score for test " + i + ": ");
            testScore += keyboard.nextDouble();
        }
        
        // print average
        System.out.println("The average test score is " + testScore/testsToScore);
    }
}
