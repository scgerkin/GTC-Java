/*
 * PoliceOfficer class definition
 */
package lesson13lab12program2;

/**
 *  This class creates a PoliceOfficer object to simulate a police officer
 *  inspecting parked cars
 */
public class PoliceOfficer {
    
    // instance variables
    private String fname;
    private String lname;
    private int badgeNum;
    
    /**
     * Default no argument constructor
     */
    public PoliceOfficer() {
        fname = "";
        lname = "";
        badgeNum = 0;
    }
    
    /**
     * default constructor with parameters 
     * @param fname     officer first name
     * @param lname     officer last name
     * @param badgeNum  officer badge number
     */
    public PoliceOfficer(String fname, String lname, int badgeNum) {
        this.fname = fname;
        this.lname = lname;
        this.badgeNum = badgeNum;
    }
    
    /**
     * copy constructor
     * @param obj   object to copy
     */
    public PoliceOfficer(PoliceOfficer obj) {
        this.fname = obj.fname;
        this.lname = obj.lname;
        this.badgeNum = obj.badgeNum;
    }
    
    /**
     * Function to determine if a ticket will be issued
     * @param carObj        car object to work on
     * @param meterObj      meter object to work on
     * @return              if minutes parked more than purchased, return true
     */
    public boolean determineTicket(ParkedCar carObj, ParkingMeter meterObj) {
        return carObj.getMinsParked() - meterObj.getMinsPurchased() > 0;
    }
    
    /**
     * getter to get a ParkingTicket object
     * @param carObj        car object to work on
     * @param meterObj      meter object to work on
     * @return              new ParkingTicket object
     */
    public ParkingTicket issueTicket(ParkedCar carObj, ParkingMeter meterObj) {
        return new ParkingTicket(carObj, meterObj, PoliceOfficer.this);
    }
    
    /**
     * getter for officer last name
     * @return 
     */
    public String getLName() {
        return lname;
    }
    
    /**
     * getter for officer first name
     * @return 
     */
    public String getFName() {
        return fname;
    }
    
    /**
     * getter for officer bade number
     * @return 
     */
    public int getBadge() {
        return badgeNum;
    }
}
