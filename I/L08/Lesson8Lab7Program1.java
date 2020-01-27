/*
    Name: Stephen Gerkin
    Date: 03/05/2019
    Lesson 8 Lab 7 Program 1
    Program Title:
        Rectangle Area
    Program Description:
        Program gets the length and width of a rectangle
        Calculates the area
        Displays the dimensions and area
 */

package lesson8lab7program1;

import java.util.Scanner;

/**
 * Class definition
 */
public class Lesson8Lab7Program1 {

    // global scanner object for input
    public static Scanner kb = new Scanner(System.in);

    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args)
    {
       double length,    // The rectangle's length
              width,     // The rectangle's width
              area;      // The rectangle's area

       // Get the rectangle's length from the user.
       length = getLength();

       // Get the rectangle's width from the user.
       width = getWidth();

       // Get the rectangle's area.
       area = getArea(length, width);

       // Display the rectangle data.
       displayData(length, width, area);
    }

    /**
     * Name: getLength
     * @return length
     * Description: gets length from user
     */
    public static double getLength() {
        String outputMsg = "Enter the length of the rectangle: ";
        System.out.print(outputMsg);
        return Double.parseDouble(kb.nextLine());
    }

    /**
     * Name: getWidth
     * @return width
     * Description: gets width from user
     */
    public static double getWidth() {
        String outputMsg = "Enter the width of the rectangle: ";
        System.out.print(outputMsg);
        return Double.parseDouble(kb.nextLine());
    }

    /**
     * Name getArea
     * @param length of rectangle
     * @param width of rectangle
     * @return area of rectangle
     * Description: calculates area of rectangle
     */
    public static double getArea(double length, double width) {
        return length * width;
    }

    /**
     * Name: displayData
     * @param length of rectangle
     * @param width of rectangle
     * @param area  of rectangle
     * Description: displays rectangle data
     */
    public static void displayData(double length, double width, double area) {
        String[] outputMsg = new String[3];
        outputMsg[0] = "The length of the rectangle is " + length;
        outputMsg[1] = "The width of the rectangle is " + width;
        outputMsg[2] = "The area of the rectangle is " + area;
        
        for (int i = 0; i <= 2; i++ )
            System.out.println(outputMsg[i]);
    }
}
