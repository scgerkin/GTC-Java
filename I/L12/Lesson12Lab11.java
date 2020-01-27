/*
Name: Stephen Gerkin
Date: 03/25/2019
Lesson 11 Lab 10 Program 1
Program Title:
    Divisions Class
Program Description:
    This program creates a class called Divisions that creates a 2D array to
    hold sales data for a company of 6 divisions for 4 quarters and demonstrates
    the class methods
 */
package lesson12lab11;

// program dependencies
import java.util.Scanner;
import java.text.DecimalFormat;


/**
 *  This program demonstrates the Divisions class
 */
public class Lesson12Lab11 {

    // global scanner, string, and decimal format objects
    public static Scanner kb = new Scanner(System.in);
    public static String outMsg;
    public static DecimalFormat dollar = new DecimalFormat("$###,##0.00");
    
    // global constants
    public static final double MIN_SALES = 0.00;
    public static final int MAX_DIVISIONS = 6;
    public static final int MAX_QUARTERS = 4;
    
    /**
     * Main method definition
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // create new Divisions object
        Divisions demoDivisions = new Divisions();
        
        // get input for sales figures
        for (int div = 0; div < MAX_DIVISIONS; div++) {
            for (int qtr = 0; qtr < MAX_QUARTERS; qtr++) {
                getSales(demoDivisions, div, qtr);
            }
            System.out.println();
        }
    
        System.out.println("\n");
        
        // display sales by division w/ quarterly increase figures
        outMsg = "Sales by division:\n";
        System.out.println(outMsg);
        
        for (int div = 0; div < MAX_DIVISIONS; div++) {
            outMsg = "Division " + (div + 1) + " total: "
                   + dollar.format(getDivisionSales(demoDivisions, div));
            System.out.println(outMsg);
            
            outMsg = "Quarterly Increases:";
            System.out.println(outMsg);
            
            outMsg = "";
            for (int qtr = 1; qtr < MAX_QUARTERS; qtr++) {
                outMsg += "Q" + (qtr + 1) + ": "
                        + dollar.format(demoDivisions.getQuarterlyIncrease(div, qtr))
                        + "\n";
            }
            System.out.println(outMsg);

        }
    
        System.out.println();
        
        // display sales by quarter with increase figures, avg sales, and
        // number of the highest sales division
        outMsg = "Sales by Quarter:\n";
        System.out.println(outMsg);
        
        for (int qtr = 0; qtr < MAX_QUARTERS; qtr++) {
            outMsg = "Quarter " + (qtr+1) + " total sales: ";
            outMsg += dollar.format(demoDivisions.totalQuarterSales(qtr));
            System.out.println(outMsg);
            
            if (qtr >= 1) {
                outMsg = "Increase of: "
                       + dollar.format(getTotalQtrIncrease(demoDivisions, qtr));
                System.out.println(outMsg);
            }
            
            outMsg = "Average sales: "
                    + dollar.format(getAvgQtrSales(demoDivisions, qtr));
            System.out.println(outMsg);
            
            outMsg = "Highest Sales Division: "
                    + (getHighestDiv(demoDivisions, qtr)+1);
            System.out.println(outMsg);
            
            System.out.println();

            // terminate JVM with success
            System.exit(0);
        }
        
    }
    
    /**
     * function to get the array index for division with highest sales
     * @param obj   object to work on
     * @param qtr   quarter number as array index
     * @return      array index of division with highest sales
     */
    public static int getHighestDiv(Divisions obj, int qtr) {
        int highestDiv = 0;
        
        for (int i = 0; i < MAX_DIVISIONS; i++) {
            if (obj.getSales(i, qtr) > obj.getSales(highestDiv, qtr)) {
                highestDiv = i;
            }
        }
        
        return highestDiv;
    }
    
    /**
     * function to get the average sales for a quarter for the entire company
     * @param obj   object to work on
     * @param qtr   quarter number as array index
     * @return      average sales for a quarter
     */
    public static double getAvgQtrSales(Divisions obj, int qtr) {
        double avgQtrSales = 0.00;
        
        for (int div = 0; div < MAX_DIVISIONS; div++) {
            avgQtrSales += obj.getSales(div, qtr);
        }
        
        avgQtrSales /= MAX_DIVISIONS;
        
        return avgQtrSales;
    }
    
    /**
     * function to get the total quarterly increase for the company
     * @param obj   object to work on
     * @param qtr   quarter number as array index
     * @return      total quarterly increase for the company
     */
    public static double getTotalQtrIncrease(Divisions obj, int qtr) {
        double totalQtrIncrease = 0.00;
        for (int div = 0; div < MAX_DIVISIONS; div++) {
            totalQtrIncrease += obj.getQuarterlyIncrease(div, qtr);
        }
        
        return totalQtrIncrease;
    }
    
    /**
     * function to display the quarterly increases for a division
     * @param obj   object to work on
     * @param div   division array index to work on
     */
    public static void displayDivisionIncrease(Divisions obj, int div) {
        outMsg = "Quarterly increase: ";
        
        for (int qtr = 1; qtr < MAX_QUARTERS; qtr++) {
            outMsg += "Q" + (qtr + 1)
                   + dollar.format(obj.getQuarterlyIncrease(div, qtr));
        }
        
        System.out.println(outMsg);
    }
    
    /**
     * function to get the total sales for a division
     * @param obj   object to work on
     * @param div   division array index to work on
     * @return      total sales for a division
     */
    public static double getDivisionSales(Divisions obj, int div) {
        double divisionSales = 0.00;
        
        for (int qtr = 0; qtr < MAX_QUARTERS; qtr++) {
            divisionSales += obj.getSales(div, qtr);
        }
        
        return divisionSales;
    }
    
    /**
     * function to get sales numbers from user
     * @param obj   object to work on
     * @param div   division array index to work on
     * @param qtr   quarter array index to work on
     */
    public static void getSales(Divisions obj, int div, int qtr) {
        outMsg = "Enter the total sales for quarter " + (qtr + 1)
               + " for division " + (div + 1) + ": $";
        System.out.print(outMsg);
        double amt = Double.parseDouble(kb.nextLine());
        
        if (amt < MIN_SALES) {
            outMsg = "Invalid input. Sales values cannot be a negative value.";
            System.out.println(outMsg);
            
            getSales(obj, div, qtr);
        }
        else
            obj.setSales(div, qtr, amt);
    }
}
