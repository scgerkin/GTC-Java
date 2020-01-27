/*
 * Divisions class definition
 */
package lesson12lab11;

/**
 *  Divisions class creates an object for storing company sales figures by 
 * division number and quarter number
 */
public class Divisions {
    
    // class constants
    private final int DIVISIONS = 6;
    private final int QUARTERS = 4;
    
    // class 2d array
    private double[][] sales = new double[DIVISIONS][QUARTERS];
    
    /**
     * Default constructor sets all values to 0;
     */
    public Divisions() {
        for (int i = 0; i < DIVISIONS; i++) {
            for (int j = 0; j < QUARTERS; j++) {
                sales[i][j] = 0.00;
            }
        }
    }
    
    /**
     * mutator for setting sales values in array placement
     * @param div   division number
     * @param qtr   quarter number
     * @param amt   sales amount
     */
    public void setSales(int div, int qtr, double amt) {
        sales[div][qtr] = amt;
    }
    
    /**
     * getter for sales amount in Divisions array
     * @param div   division number
     * @param qtr   quarter number
     * @return      sales amount for division and quarter
     */
    double getSales(int div, int qtr) {
        return sales[div][qtr];
    }
    
    /**
     * getter for total quarterly sales
     * @param quarter   quarter number to get
     * @return          total quarterly sales
     */
    double totalQuarterSales(int quarter) {
        double totalQuarterSales = 0.00;
        
        for (int i = 0; i < DIVISIONS; i++) {
            totalQuarterSales += sales[i][quarter];
        }
        
        return totalQuarterSales;
    }
    
    /**
     * getter for quarterly increase numbers, returns 0 if first quarter is sent
     * as argument
     * @param div   division to work on
     * @param qtr   quarter to work on
     * @return      quarterly increase
     */
    double getQuarterlyIncrease(int div, int qtr) {
        
        if (qtr <= 0) {
            return 0.00;
        }
        else {
            return sales[div][qtr]-sales[div][qtr-1];
        }
    }
}
