package payrollclass;

/**
 * Payroll Class
 * Holds payroll information for 7 Employees
 * Provides methods for accessing information about each employee
 * Provides methods for changing information about each employee
 */
public class Payroll {
    public final int NUM_EMPLOYEES = 7;

    public int[] employeeID = {
        5658845, 4520125, 7895122, 8777541, 8451277, 1302850, 7580489
    };
    private int[] hours = new int[NUM_EMPLOYEES];
    private double[] payRate = new double[NUM_EMPLOYEES];
    private double[] wages = new double[NUM_EMPLOYEES];

    /**
     * Default constructor
     */
    public Payroll() {
        for (int i = 0; i < NUM_EMPLOYEES; i++) {
            this.hours[i] = 0;
            this.payRate[i] = 0;
            this.wages[i] = 0;
        }
    }

    /**
     * Mutator for setting hours worked
     * @param i     index location to change
     * @param val   value to set
     */
    public void setHours(int i, int val) {
        this.hours[i] = val;
    }

    /**
     * Mutator for setting pay rate
     * @param i    index location to change
     * @param val  value to set
     */
    public void setPayRate(int i, double val) {
        this.payRate[i] = val;
    }

    /**
     * Mutator for setting gross wages
     * @param i   index location to change
     */
    public void setWages(int i) {
        this.wages[i] = hours[i] * payRate[i];
    }

    /**
     * Accessor for hours worked
     * @param employeeID    ID number to get hours for
     * @return int          hours worked if found, else -1
     */
    public int getHours(int employeeID) {
        int index = getEmpLoc(employeeID);

        if (index < 0) {
            return -1;
        }
        else {
            return this.hours[index];
        }
    }

    /**
     * Accessor for pay rate
     * @param employeeID    ID number to get pay rate for
     * @return double   return pay rate if found, else -1
     */
    public double getPayRate(int employeeID) {
        int index = getEmpLoc(employeeID);

        if (index < 0) {
            return -1;
        }
        else {
            return this.payRate[index];
        }
    }

    /**
     * Accessor for wages
     * @param employeeID    ID number to get wages for
     * @return double   wages if found, else -1
     */
    public double getWages(int employeeID) {
        int index = getEmpLoc(employeeID);

        if (index < 0) {
            return -1;
        }
        else {
            return this.wages[index];
        }
    }

    /**
     * Search method to find index of element in array via employee ID
     * @param searchVal     value to search for
     * @return int          index of element if found, else -1
     */
    private int getEmpLoc(int searchVal) {
        int location = -1;
        boolean locFound = false;
        int i = 0;

        while (!locFound && i < NUM_EMPLOYEES) {
            if (searchVal == this.employeeID[i]) {
                location = i;
                locFound = true;
            }
            i++;
        }

        return location;
    }
}
