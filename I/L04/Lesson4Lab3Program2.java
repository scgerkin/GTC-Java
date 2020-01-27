/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 4 Lab 3 Program 2
    Program Title:
        Word Game
    Program Description:
        A program that prompts the user to enter some information and display a
        story from that information

*/
package lesson.pkg4.lab.pkg3.program.pkg2;

import javax.swing.JOptionPane;

/**
* Class definition
*/
public class Lesson4Lab3Program2 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // declare local variables
        String userName,
               userAge,
               city,
               collegeName,
               profession,
               animal,
               petName;
        
        // get values for each variable from user
        userName = JOptionPane.showInputDialog("Enter your name.");
        userAge = JOptionPane.showInputDialog("Enter your age.");
        city = JOptionPane.showInputDialog("Enter the name of a city.");
        collegeName = JOptionPane.showInputDialog("Enter the name of a college.");
        profession = JOptionPane.showInputDialog("Enter a profession.");
        animal = JOptionPane.showInputDialog("Enter a type of animal.");
        petName = JOptionPane.showInputDialog("Enter a pet's name");

        // display story
        JOptionPane.showMessageDialog(
            null,
            "There once was a person named " + userName + " who lived in " + city + ".\n" +
            "At the age of " + userAge + ", " + userName + " went to college at " + collegeName + ".\n" +
            userName + " graduated and went to work as a " + profession + ".\n" +
            "Then, " + userName + " adopted a(n) " + animal + " named " + petName + ".\n" +
            "They both lived happily ever after!"
        );
    }
}
