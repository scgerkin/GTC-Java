package population;

import java.util.Scanner;
import java.lang.Math;

/**
 *  @author Stephen Gerkin
 *  @date 2019-05-15
 *  Programming Lab 1 - 9. Population
 *  Program predicts size of population of organisms based on user input
 */
public class Population {

    // class Scanner obj
    private static Scanner kb = new Scanner(System.in);

    /**
     * Main method wrangles program methods
     * @param args  command line args
     */
    public static void main(String[] args) {
        // get vals
        long numOrganisms = getOrganisms();
        double growthRate = getDailyPopIncrease();
        int numDays = getNumDays();

        // disp size and increment starting at day 0 to num days
        for (int i = 0; i <= numDays; i++) {
            numOrganisms = calcPopulation(numOrganisms, growthRate, i);
            dispPopSize(i, numOrganisms);
        }

        System.exit(0);
    }

    /**
     * Method to get starting number of organisms from user
     * @return  long number of organisms
     */
    public static long getOrganisms() {
        // get input
        System.out.println("Enter the starting number of organisms:");
        long numOrganisms = Long.parseLong(kb.nextLine());

        // validate entry, recur if invalid
        if (numOrganisms < 2) {
            System.out.println(
                "Invalid entry. Please enter an integer of at least 2."
            );
            return getOrganisms();
        }

        return numOrganisms;
    }

    /**
     * Method to get average daily population increase
     * Takes in entry as percent value
     * @return double   as decimal value
     */
    public static double getDailyPopIncrease() {
        // get input
        System.out.println(
            "Enter the average daily population increase (as percentage):"
        );
        double avgPopIncrease = Double.parseDouble(kb.nextLine());

        // validate entry, recur if invalid
        if (avgPopIncrease < 0.0) {
            System.out.println(
                "Invalid entry. Please enter a positive number value."
            );
            return getDailyPopIncrease();
        }

        return avgPopIncrease * 0.01;
    }

    /**
     * Method to get number of days organisms are to multiple
     * @return int
     */
    public static int getNumDays() {
        // get input
        System.out.println(
            "Enter the number of days the organisms will multiply."
        );
        int numDays = Integer.parseInt(kb.nextLine());

        // validate entry, recur if invalid
        if (numDays < 1) {
            System.out.println(
                "Invalid entry. Please enter an integer of at least 1."
            );
            return getNumDays();
        }

        return numDays;
    }

    /**
     * Method to calculate population increase
     * Uses simple population growth equation:
     * P(sub future) = P(sub current) * (1 + i)^n
     * @param popCurr   Current population size
     * @param rate      Avg daily population increase (as decimal)
     * @param days      Number of days to calc for
     * @return long     Predicted size after increase
     */
    public static long calcPopulation(long popCurr, double rate, int days) {
        // P(sub future) = P(sub current) * (1 + i)^n
        double popFuture = popCurr * (Math.pow((1+rate), days));

        // return as integer, can't have a decimal amount of an organism
        return (long)popFuture;
    }

    /**
     * Method to display population size
     * @param day   Current day
     * @param size  Current size
     */
    public static void dispPopSize(int day, long size) {
        System.out.println(
            "On day " + day + ", the population is: " + size + "."
        );
    }

}
