/*
 * ParkingTicket class definition
 */
package lesson13lab12program2;

import java.text.DecimalFormat;

/**
 *  This class creates an object for simulating a parking ticket
 */
public class ParkingTicket {

    // decimal format object
    public static DecimalFormat dollar = new DecimalFormat("$###,##0.00");
    
    // constants
    public final double FINE_FIRST_HOUR = 25.00;
    public final double FINE_ADDITIONAL = 10.00;
    
    // instance objects
    private ParkedCar car;
    private ParkingMeter meter;
    private PoliceOfficer officer;
    
    // instance variable for fine
    private double fine;
    

    /**
     * Copy constructor for Parking Ticket object
     * @param ticketObj 
     */
    public ParkingTicket(ParkingTicket ticketObj) {
        this.car = ticketObj.car;
        this.meter = ticketObj.meter;
        this.officer = ticketObj.officer;
        this.fine = ticketObj.fine;
        
    }
    
    /**
     * default constructor taking objects for construction
     * initializes fine as 0.00
     * @param carObj        ParkedCar object to construct
     * @param meterObj      ParkingMeter object to construct
     * @param officerObj    PoliceOfficer object to construct
     */
    public ParkingTicket(ParkedCar carObj, ParkingMeter meterObj,
                         PoliceOfficer officerObj) {
        
        this.car = new ParkedCar(carObj);
        this.meter = new ParkingMeter(meterObj);
        this.officer = new PoliceOfficer(officerObj);
        
        this.fine = determineFine();
    }
    
    /**
     * Function to determine a fine amount
     * @return 
     */
    private double determineFine() {
        int timeInfraction;
        
        timeInfraction = car.getMinsParked() - meter.getMinsPurchased();
        
        if (timeInfraction > 0) {
            fine = FINE_FIRST_HOUR;
            timeInfraction -= 60;
        }
        
        if (timeInfraction > 0) {
            fine += (timeInfraction / 60) * FINE_ADDITIONAL;
        }
        
        return fine;
    }
    
    /**
     * getter to print officer information for ParkingTicket instance
     */
    public void reportOfficer() {
        String outMsg;
        outMsg = "Officer Name: " + officer.getLName() + ", "
               + officer.getFName() + "\n"
               + "Badge Number: " + officer.getBadge();
        System.out.println(outMsg);
    }
    
    /**
     * getter to print fine information for ParkingTicket instance
     */
    public void reportFine() {
        String outMsg;
        outMsg = "The total fine is: " + dollar.format(fine);
        System.out.println(outMsg);
    }
    
    /**
     * getter to print car information for ParkingTicket instance
     */
    public void reportCar() {
        String outMsg;
        outMsg = "Make: " + car.getMake() + "\n"
               + "Model: " + car.getModel() + "\n"
               + "License Number: " + car.getLicense() + "\n"
               + "Minutes Parked: " + car.getMinsParked() + "\n";
        System.out.println(outMsg);
    }
       
}
