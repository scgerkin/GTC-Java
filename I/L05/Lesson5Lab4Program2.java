/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 5 Lab 4 Program 2
    Program Title:
        Fat Gram Calculator
    Program Description:
        Gets number of calories in food item from user
        Calculates grams of fat and displays result
        If grams of fat less than 9, displays that item is low in fat

 */

package lesson.pkg5.lab.pkg4.program.pkg2;

import javax.swing.JOptionPane;

/**
 * Class definition
 */
public class Lesson5Lab4Program2 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        
        // local variables
        int calories,
            fatGrams;
        
        double caloriesFromFat,
               percentCaloriesFromFat;
        
        // get calories
        calories = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of calories in item."));
        
        // get fat grams
        fatGrams = Integer.parseInt(JOptionPane.showInputDialog("Enter the grams of fat in item."));
        
        // calculations
        caloriesFromFat = fatGrams*9;
        percentCaloriesFromFat = (caloriesFromFat / calories) * 100;
        
        // formatted output string
        String output = String.format("The percentage of calories that come from fat are %.2f", percentCaloriesFromFat);
        
        // determine if low fat item and display
        if (percentCaloriesFromFat < 30) {
            JOptionPane.showMessageDialog(null,output + "%.\n" + "This is a low fat item.");
        }
        else {
            JOptionPane.showMessageDialog(null,output + "%.");
        }
    }
}
