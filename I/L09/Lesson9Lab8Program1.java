/*
    Name: Stephen Gerkin
    Date: 3/6/2019
    Lesson 9 Lab 8 Program 1
    Program Title:
        TestScores class
    Program Description:
        This program creates a class called TestScores and demonstrates the
        methods of the class with 2 objects
 */

package lesson9lab8program1;

import java.util.Scanner;

/**
 * This program demonstrates the TestScores class with 2 objects
 */
public class Lesson9Lab8Program1 {

    // global scanner object
    public static Scanner kb = new Scanner(System.in);
    
    /**
     * Main method definition
     * @param args
     */
    public static void main(String[] args) {
        String outputMsg;       // used for output messages
        double newScore;        // used for storing input from user
        
        // create an object with no arguments
        TestScores bio = new TestScores();
        
        // create an object with arguments
        TestScores algebra = new TestScores(88.8, 95.7, 99.9);
        
        // demonstrate the no-arg constructor and accessor method
        outputMsg = "The scores for biology are currently:\n"
                  + bio.getScore(1) + " for test 1\n"
                  + bio.getScore(2) + " for test 2\n"
                  + bio.getScore(3) + " for test 3\n"
                  + "The average test score is " + bio.calcAverage() + "%.";
        System.out.println(outputMsg);
        
        // demonstrate the constructor overload and accessor method
        outputMsg = "The scores for algebra are currently:\n"
                  + algebra.getScore(1) + " for test 1\n"
                  + algebra.getScore(2) + " for test 2\n"
                  + algebra.getScore(3) + " for test 3\n"
                  + "The average test score is " + algebra.calcAverage() + "%.";
        System.out.println(outputMsg);
        
        // demonstrate the mutator setScore with for loop
        for (int i = 1; i <= 3; i++) {
            outputMsg = "Enter the new test score for biology test " + i + ": ";
            System.out.println(outputMsg);
            newScore = Double.parseDouble(kb.nextLine());
            bio.setScore(i, newScore);
        }
        
        // demonstrate the test scores have changed
        outputMsg = "The new test scores for biology are now:\n";
        for (int i = 1; i <=3; i++) {
            outputMsg += bio.getScore(i) + " for test " + i + "\n";
        }
        System.out.println(outputMsg);
        
        // demonstrate the average has changed
        outputMsg = "The average test score for biology is now "
                  + bio.calcAverage() + "%.";
        System.out.println(outputMsg);
    }
}
