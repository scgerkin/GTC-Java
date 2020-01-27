package sumofdigitsinstring;

import java.util.Scanner;

/**
 * @author Stephen Gerkin
 * @date 2019-05-23
 * Programming Lab 4 - 9. Sum of Digits in a String
 * Program to get a string if digits from the user, display the sum, min, and
 * max values of the input
 */
public class Main {

    // class Sanner object for keyboard input
    private static final Scanner kb = new Scanner(System.in);

    /**
     * Private inner class for storing an integer tuple
     */
    private static class IntTuple {
        int sum;
        int max;
        int min;

        IntTuple(int varSum, int varMin, int varMax) {
            this.sum = varSum;
            this.min = varMin;
            this.max = varMax;
        }
    }

    /**
     * Main method driver for program methods
     * @param args command line args
     */
    public static void main(String[] args) {
        String userInput = getInput();

        int[] inputArr = strToIntArr(userInput);

        IntTuple results = getSumMinMax(inputArr);

        printResults(userInput, results);

        System.exit(0);
    }

    /**
     * Method to get input from the user
     * @return user input
     */
    public static String getInput() {
        System.out.println(
            "Enter a series of single digit numbers with nothing "
            + "sepearating them."
        );

        return kb.nextLine();
    }

    /**
     * Converts a String into an array of integers
     * @param str string to convert
     * @return integer array of input
     */
    public static int[] strToIntArr(String str) {
        String[] strArr = str.split("");
        int[] intArr = new int[strArr.length];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        return intArr;
    }

    /**
     * Gets the sum, min, and max of an integer array
     * @param arr array to work on
     * @return IntTuple consisting of the sum, min, and max
     */
    public static IntTuple getSumMinMax(int[] arr) {
        int sum = 0;
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }

        return new IntTuple(sum, min, max);
    }

    /**
     * Display method
     * @param input Original user input
     * @param results IntTuple consisting of the sum, min, and max values
     */
    public static void printResults(String input, IntTuple results) {
        System.out.println(
            "The value you entered is: " + input + "\n"
            + "The sum of your entry is: " + results.sum + "\n"
            + "The minimum value is: " + results.min + "\n"
            + "The maximum value is: " + results.max + "\n"
        );
    }
}
