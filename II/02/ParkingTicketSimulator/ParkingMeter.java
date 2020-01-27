package parkingticketsimulator;

/**
 * ParkingMeter holds information about a parking meter
 * Provides methods for getting and setting member variables
 */
public class ParkingMeter {
    private int minsPurchased;

    /**
     * Default no-arg constructor
     */
    public ParkingMeter() {
        this.minsPurchased = 0;
    }

    /**
     * Default constructor with minsPurchased as param
     * @param mins
     */
    public ParkingMeter(int mins) {
        this.minsPurchased = mins;
    }

    /**
     * Copy constructor
     * @param obj
     */
    public ParkingMeter(ParkingMeter obj) {
        this.minsPurchased = obj.minsPurchased;
    }

    /**
     * Setter for this.minsPurchased
     * @param n
     */
    public void setMinsPurchased(int mins) {
        this.minsPurchased = mins;
    }

    /**
     * Getter
     * @return this.minsPurchased
     */
    public int getMinsPurchased() {
        return this.minsPurchased;
    }
}
