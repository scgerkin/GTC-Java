/*
    Name: Stephen Gerkin
    Date: 02/09/2019
    Lesson 6 Lab 5 Program 2
    Program Title:
        Population
    Program Description:
        Program to predict the population of organisms
        
 */

package lesson.pkg6.lab.pkg5.program.pkg2;

import javax.swing.JOptionPane;

/**
 * Class definition
 */
public class Lesson6Lab5Program2 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        long organisms;             // number of organisms
        int numerDays;              // number of days to populate
        double percentIncrease;     // percent increase of population
        
        // get starting population, validate that it is greater than 2, loop until valid entry
        do {
            organisms = Long.parseLong(JOptionPane.showInputDialog("Enter the starting number of organisms (day 0)."));

            if (organisms < 2) {
                JOptionPane.showMessageDialog(null,"You need at least 2 organisms to propogate.");
            }
        } while (organisms < 2);
        
        // get population percent increase, validate it is greater than 0, loop until valid entry
        do {
            percentIncrease = Double.parseDouble(JOptionPane.showInputDialog("Enter the daily population increase as a percentage."));
            
            if (percentIncrease < 0) {
                JOptionPane.showMessageDialog(null,"Population increase cannot be negative.\nThat would be a decrease.");
            }
        } while (percentIncrease < 0);
        
        // convert to decimal
        percentIncrease /= 100;
                
        // get number of days to populate, validate it is greater than 0, loop until valid entry
        do {
            numerDays = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of days the organisms will multiply"));
            
            if (numerDays < 1) {
                JOptionPane.showMessageDialog(null,"Time doesn't work backwards. Entry must be at least 1.");
            }
        } while (numerDays < 1);
        
        // display population for each day
        for (int i = 1; i <= numerDays; i++) {
            organisms += (organisms *= percentIncrease);
            JOptionPane.showMessageDialog(null,"The population at day " + i + " is " + organisms + " organisms.");
        }
    }

}
