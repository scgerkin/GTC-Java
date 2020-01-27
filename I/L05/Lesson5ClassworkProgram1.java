/*
    Name: Stephen Gerkin
    Date: 02/06/2019
    Lesson 4 Classwork Program 1
    Program Title:
        Internet Service Provider
    Program Description:
        Program to get hours of internet use in a month and ISP package
        Determines charges based on package and hours used

 */

package lesson.pkg5.classwork.program.pkg1;

import javax.swing.JOptionPane;

/**
 * Class definition
 */
public class Lesson5ClassworkProgram1 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        
        // local variables
        char ispPackage;
        int hoursUsed;
        double charges = 0.00;
        
        // get isp package and hours used
        ispPackage = Character.toUpperCase(JOptionPane.showInputDialog("Enter the ISP Package purchased (A/B/C)").charAt(0));
        hoursUsed = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of hours used this month."));
        
        // determine package and calc charges
        switch (ispPackage) {
            case 'A':
                charges = 9.95;
                if (hoursUsed >10) {
                    charges += ((hoursUsed-10)*2.00);
                }
                break;
            case 'B':
                charges = 13.95;
                if (hoursUsed > 20) {
                    charges +=((hoursUsed-20)*1.00);
                }
                break;
            case 'C':
                charges = 19.95;
                break;
            default:
                JOptionPane.showMessageDialog(null, "That was not a valid entry.");
        }
        
        // format output
        String output = String.format("The charges incurred are $%.2f.", charges);
        
        // display charges
        JOptionPane.showMessageDialog(null, output);
        
        
    }

}
