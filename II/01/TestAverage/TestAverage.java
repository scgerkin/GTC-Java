package testaverage;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *  @author Stephen Gerkin
 *  @date   2019-05-14
 *  Programming Lab 1 - 7. Test Average and Grade
 *  Gets a number of test scores from user
 *  Displays letter grade for each test
 *  Calculates average grade
 *  Displays average grade and letter
 */
public class TestAverage {

    // class I/O objs
    private static Scanner kb = new Scanner(System.in);

    /**
     * Main method wrangles program methods
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create array for tests
        final int TOTAL_TESTS = 5;
        double[] scores = new double[TOTAL_TESTS];

        // get scores and display grade
        for (int i = 0; i < TOTAL_TESTS; i++) {
            scores[i] = getGrade(i);
            displayGrade(i, determineGrade(scores[i]));
        }

        // calc and disp avg
        double average = calcAverage(scores, TOTAL_TESTS);
        displayAverage(average);

        System.exit(0);
    }

    /**
     * Gets score for test from user
     * Rejects scores less than 0
     * @param testNum   Test to get score for
     * @return double   Score
     */
    public static double getGrade(int testNum) {
        // get input
        System.out.println("Enter the grade for test " + (testNum + 1) + ": ");
        double grade = Double.parseDouble(kb.nextLine());

        // validate entry, recur if invalid
        if (grade < 0) {
            System.out.println("Invalid input. Please enter a valid grade.");
            return getGrade(testNum);
        }

        return grade;
    }

    /**
     * Determines letter grade for score
     * @param score Score to determine
     * @return char Letter grade
     */
    public static char determineGrade(double score) {
        if (score < 60) {
            return 'F';
        }
        if (score < 70) {
            return 'D';
        }
        if (score < 80) {
            return 'C';
        }
        if (score < 90) {
            return 'B';
        }
        // if function gets here, grade is A
        return 'A';
    }

    /**
     * Calculates average score for all tests
     * @param arr   Array of tests to average
     * @param size  Total number of tests to average
     * @return double   Average test score
     */
    public static double calcAverage(double[] arr, int size) {
        double avg = 0.0;

        for (int i = 0; i < size; i++) {
            avg += arr[i];
        }

        return (avg / size);
    }

    /**
     * Displays letter grade for test
     * @param testNum   Test number to display
     * @param grade     Grade to display
     */
    public static void displayGrade(int testNum, char grade) {
        System.out.println(
            "The grade for "
            + ((testNum >= 0) ? "test " + (testNum + 1) : "the average")
            + " is "
            + ((grade == 'A' || grade == 'F') ? "an " : "a ")
            + grade + ".\n"
        );
    }

    /**
     * Displays average score and letter grade
     * @param avg   Average test scores
     */
    public static void displayAverage(double avg) {
        DecimalFormat df = new DecimalFormat("##0.00%");
        df.setMultiplier(1);

        System.out.println("The average score is " + df.format(avg) + ".");

        displayGrade(-1, determineGrade(avg));
    }
}
