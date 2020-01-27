/*
    Name: Stephen Gerkin
    Date: 02/26/2019
    Lesson 7 Lab 6 Program 1
    Program Title:
        Sorted names
    Program Description:
        Program to get names as input from user, sort, and display.
 */

package lesson7lab6program1;

import javax.swing.JOptionPane;
import java.util.Arrays;

/**
 * Class definition
 */
public class Lesson7Lab6Program1 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        int numberNames;
        String outputMsg = "How many names would you like to enter?";
        
        // get # of names to enter from user
        numberNames = Integer.parseInt(JOptionPane.showInputDialog(outputMsg));
        
        // declare array as size indicated by user
        String[] nameList = new String[numberNames];
        
        // get names
        for (int i = 0; i < numberNames; i++) {
            nameList[i] = GetName(i+1);
        }
        
        // sort names
        Arrays.sort(nameList);
        
        // display names
        for (int i = 0; i < numberNames; i++) {
            System.out.println(nameList[i]);
        }
        
        System.exit(0);
    }
    
    /**
     * Name:
     *  GetName
     * Parameters:
     *  @param namePos  number position of name to get
     * Return:
     *  @return Name input
     * Description:
     *  Gets name input from user and returns it
     */
    public static String GetName(int namePos) {
        String userInput;
        String outputMsg = "Enter name number " + namePos+ ":";
        
        return JOptionPane.showInputDialog(outputMsg);
    }
}
