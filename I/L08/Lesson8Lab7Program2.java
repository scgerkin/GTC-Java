/*
    Name: Stephen Gerkin
    Date: 3/5/2019
    Lesson 8 Lab 7 Program 2
    Program Title:
        Test average
    Program Description:
        Gets 5 test scores from the user
        Displays the letter grade for each
        Calculates and displays the average test score
 */

package lesson8lab7program2;

import java.util.Scanner;

public class Lesson8Lab7Program2 {

    // global scanner object for input
    public static Scanner kb = new Scanner(System.in);
    
    // global variable for # of tests to get
    public static int TOTAL_TESTS = 5;
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) {
        double[] testScore = new double[TOTAL_TESTS];  // array for tests
        String outputMsg;                           // output msg build var
        double average;                             // average of tests
                
        // get test scores to fill array and display grade for each
        for (int i = 0; i < TOTAL_TESTS; i++){
            testScore[i] = getGrade(i);
            outputMsg = "The grade for test " + i+1 + " is: "
                      + determineGrade(testScore[i]);
            System.out.println(outputMsg);
        }
        
        // calculate the average
        average = calcAverage(testScore);
        
        // display average
        outputMsg = "The average test score is: " + average + "%."
                  + "\n"
                  + "The grade for your test average is: "
                  + determineGrade(average);
        System.out.println(outputMsg);
    }

    /**
     * Name: getGrade
     * @param testNum test number to get
     * @return test score input
     * Description: gets a grade from the user
     */
    public static double getGrade(int testNum) {
        String outputMsg = "Enter the grade for test " + (testNum +1) + ": ";
        System.out.print(outputMsg);
        return Double.parseDouble(kb.nextLine());
    }
    
    /**
     * Name: determineGrade
     * @param score
     * @return grade letter of test
     * Description: Determines the letter grade of a score of a test
     */
    public static char determineGrade(double score) {
        char grade;
    
        // determine grade
        if (score < 60)
            grade = 'F';
        else if (score < 70)
            grade = 'D';
        else if (score < 80)
            grade = 'C';
        else if (score < 90)
            grade = 'B';
        else
            grade = 'A';
        
        return grade;
    }
    
    /**
     * Name: calcAverage
     * @param testScore array of test scores
     * @return average of test scores
     * Description: calculates the average test score of an array of tests
     */
    public static double calcAverage(double[] testScore) {
        double average = 0;
        
        // add test scores together
        for (int i = 0; i < TOTAL_TESTS; i++) {
            average += testScore[i];
        }
        
        // return the average
        return average / TOTAL_TESTS;
    }
}
