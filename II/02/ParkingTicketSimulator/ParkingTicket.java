package parkingticketsimulator;

/**
 * ParkingTicket holds information for a parking ticket
 * Provides methods to determine and return a fine
 * Provides methods to report aggregated ParkedCar and PoliceOfficer objects
 */
public class ParkingTicket {

    // class constants
    private static final double FINE_FIRST_HOUR = 25.00;
    private static final double FINE_ADDITIONAL = 10.00;

    // aggregated instance objects
    private ParkedCar car;
    private ParkingMeter meter;
    private PoliceOfficer officer;

    /**
     * Default constructor
     * @param carObj
     * @param meterObj
     * @param officerObj
     */
    public ParkingTicket(ParkedCar carObj, ParkingMeter meterObj,
                         PoliceOfficer officerObj)
    {
        this.car = new ParkedCar(carObj);
        this.meter = new ParkingMeter(meterObj);
        this.officer = new PoliceOfficer(officerObj);
    }

    /**
     * Copy constructor
     * @param obj
     */
    public ParkingTicket(ParkingTicket obj) {
        this.car = new ParkedCar(obj.car);
        this.meter = new ParkingMeter(obj.meter);
        this.officer = new PoliceOfficer(obj.officer);
    }

    /**
     * Getter
     * @return fine amount
     */
    public double getFine() {
        // determine how long car has been illegally parked
        int timeInfraction = (
            this.car.getMinsParked() - this.meter.getMinsPurchased()
        );

        double fine = 0;

        // if time greater than 0, set fine to initial fine amount,
        // decrement time infraction by one hour
        if (timeInfraction > 0) {
            fine += FINE_FIRST_HOUR;
            timeInfraction -= 60;
        }

        // add additional fine amount to fine,
        // decrement time infraction by one hour for each loop
        while (timeInfraction > 0) {
            fine += FINE_ADDITIONAL;
            timeInfraction -= 60;
        }

        return fine;
    }

    /**
     * Getter
     * @return ParkedCar object as string
     */
    public String getCar() {
        String str = "Make: " + this.car.getMake() + "\n"
                   + "Model: " + this.car.getModel() + "\n"
                   + "Color: " + this.car.getColor() + "\n"
                   + "License Number: " + this.car.getLicense();
        return str;
    }

    /**
     * Getter
     * @return PoliceOfficer object as string
     */
    public String getOfficer() {
        String str = "Name: " + this.officer.getName() + "\n"
                   + "Badge Number: " + this.officer.getBadgeNumber();
        return str;
    }
}
