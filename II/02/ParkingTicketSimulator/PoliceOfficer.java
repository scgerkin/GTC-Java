package parkingticketsimulator;

/**
 * PoliceOfficer holds information about a police officer
 * Provides methods for getting member variables
 * Provides method to determine if a ParkedCar obj is illegally parked
 * Provides method to issue a new ParkingTicket obj
 */
public class PoliceOfficer {

    // member variables
    private String name;
    private String badgeNumber;

    /**
     * Default constructor
     * @param n
     * @param b
     */
    public PoliceOfficer(String n, String b) {
        this.name = n;
        this.badgeNumber = b;
    }

    /**
     * Copy constructor
     * @param obj
     */
    public PoliceOfficer(PoliceOfficer obj) {
        this.name = obj.name;
        this.badgeNumber = obj.badgeNumber;
    }

    /**
     * Setter for this.name
     * @param officerName
     */
    public void setName(String officerName) {
        this.name = officerName;
    }

    /**
     * Setter for this.badgeNumber
     * @param officerBadge
     */
    public void setBadgeNumber(String officerBadge) {
        this.badgeNumber = officerBadge;
    }

    /**
     * Getter
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter
     * @return this.badgeNumber
     */
    public String getBadgeNumber() {
        return this.badgeNumber;
    }

    /**
     * Method to determine if a ticket should be issued
     * @param car   ParkedCar obj to examine
     * @param meter ParkingMeter obj to examine
     * @return True if car has been parked longer than minutes purchased
     */
    public Boolean determineTicket(ParkedCar car, ParkingMeter meter) {
        return car.getMinsParked() - meter.getMinsPurchased() > 0;
    }

    /**
     * Method to issue a new ticket
     * @param car ParkedCar obj to write ticket for
     * @param meter ParkingMeter obj car is parked at
     * @return new ParkingTicket obj
     */
    public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
        return new ParkingTicket(car, meter, PoliceOfficer.this);
    }
}
