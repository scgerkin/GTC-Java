package carpetcalculator;

import java.util.Scanner;

/**
 * @author Stephen Gerkin
 * @date 2019-05-22
 * Programming Lab 3 - 3. Carpet Calculator
 * Program to store and calculate the cost of carpet for a room
 */
public class Main {

    private static final Scanner kb = new Scanner(System.in);

    /**
     * Main method to demonstrate CarpetCalculator class
     * @param args command line args
     */
    public static void main(String[] args) {
        System.out.println("Enter the length of the room:");
        double length = Double.parseDouble(kb.nextLine());

        System.out.println("Enter the width of the room:");
        double width = Double.parseDouble(kb.nextLine());

        System.out.println("Enter the cost per square foot of the carpet:");
        double cost = Double.parseDouble(kb.nextLine());

        RoomDimension demoDimension = new RoomDimension(length, width);
        RoomCarpet demoCarpet = new RoomCarpet(demoDimension, cost);

        System.out.println(
            "The information about this room is:\n" + demoCarpet + "\n"
        );
    }
}
