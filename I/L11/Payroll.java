/*
 * Payroll class definition
 */
package lesson11lab10;

/**
 * Payroll class creates an object for storing payroll information
 */
public class Payroll {
    
    // constants for object
    public final int NUM_EMPLOYEES = 7;
    
    // array variables for object
    public int[] employeeID = {5658845, 4520125, 7895122, 8777541,
                                      8451277, 1302850, 7580489};
    
    private int[] hours = new int[NUM_EMPLOYEES];
    private double[] payRate = new double[NUM_EMPLOYEES];
    private double[] wages = new double[NUM_EMPLOYEES];
    
    /**
     * Default constructor
     */
    public Payroll() {
        for (int i = 0; i < NUM_EMPLOYEES; i++) {
            hours[i] = 0;
            payRate[i] = 0;
            wages[i] = 0;
        }
    }
    
    /**
     * mutator for setting hours worked
     * @param i index location to change
     * @param val value to set
     */
    public void setHours(int i, int val) {
        hours[i] = val;
    }
    
    /**
     * mutator for setting pay rate
     * @param i index location to change
     * @param val value to set
     */
    public void setPayRate(int i, double val) {
        payRate[i] = val;
    }
    
    /**
     * mutator for setting gross wages
     * @param i index location to change
     */
    public void setWages(int i) {
        wages[i] = hours[i] * payRate[i];
    }
    
    /**
     * getter for hours worked
     * @param employeeID    ID number to get hours for
     * @return            if found, return hours worked, return -1 if not found
     */
    public int getHours(int employeeID) {
        int index;
        
        index = getEmpLoc(employeeID);
        
        if (index < 0) {
            return -1;
        }
        else {
            return hours[index];
        }
    }
    
    /**
     * getter for pay rate
     * @param employeeID    ID number to get pay rate for
     * @return            if found, return pay rate, return -1 if not found
     */
    public double getPayRate(int employeeID) {
        int index;
        
        index = getEmpLoc(employeeID);
        
        if (index < 0) {
            return -1;
        }
        else {
            return payRate[index];
        }
    }

    
    /**
     * getter for wages
     * @param employeeID    ID number to get wages for
     * @return              if found, wages as double, return -1 if not found
     */
    public double getWages(int employeeID) {
        int index;
        
        index = getEmpLoc(employeeID);
        
        if (index < 0) {
            return -1;
        }
        else {
            return wages[index];
        }
    }
    /**
     * search function to find index of element in array via employee ID
     * @param searchVal value to search for
     * @return          index of element if found, return -1 if not found
     */
    private int getEmpLoc(int searchVal) {
        int location = -1;
        boolean locFound = false;
        int i = 0;
        
        while (!locFound && i < NUM_EMPLOYEES) {
            if (searchVal == employeeID[i]) {
                location = i;
                locFound = true;
            }
            i++;
        }
        
        return location;
    }
}
