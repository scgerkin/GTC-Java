/*
 * ParkingMeter class definition
 */
package lesson13lab12program2;

/**
 *  ParkingMeter class creates an object for simulating a parking meter
 */
public class ParkingMeter {
    
    // instance variables
    private int minsPurchased;
    
    /**
     * Default no argument constructor
     */
    public ParkingMeter() {
        minsPurchased = 0;
    }
    
    /**
     * Constructor for argument of minutes purchased
     * @param num 
     */
    public ParkingMeter(int num) {
        minsPurchased = num;
    }
    
    
    /**
     * copy constructor
     * @param obj   object to copy
     */
    public ParkingMeter(ParkingMeter obj) {
        this.minsPurchased = obj.minsPurchased;
    }
    
    /**
     * mutator for setting number of minutes purchased
     * @param num 
     */
    public void setMinsPurchased(int num) {
        minsPurchased = num;
    }
    
    /**
     * getter for minutes purchased
     * @return 
     */
    public int getMinsPurchased() {
        return minsPurchased;
    }
}
