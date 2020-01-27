package parkingticketsimulator;

/**
 * ParkedCar holds information about a parked car
 * Provides methods for getting and setting member variables
 */
public class ParkedCar {

    // member variables
    private String make;
    private String model;
    private String color;
    private String licenseNum;
    private int minsParked;

    /**
     * Default constructor
     * @param carMake
     * @param carModel
     * @param carColor
     * @param carLicense
     * @param carMinsParked
     */
    public ParkedCar(String carMake,String carModel, String carColor,
                     String carLicense, int carMinsParked) {
        this.make = carMake;
        this.model = carModel;
        this.color = carColor;
        this.licenseNum = carLicense;
        this.minsParked = carMinsParked;
    }

    /**
     * Copy constructor
     * @param obj
     */
    public ParkedCar(ParkedCar obj) {
        this.make = obj.make;
        this.model = obj.model;
        this.color = obj.color;
        this.licenseNum = obj.licenseNum;
        this.minsParked = obj.minsParked;
    }

    /**
     * Setter for this.make
     * @param m
     */
    public void setMake(String m) {
        this.make = m;
    }

    /**
     * Setter for this.model
     * @param m
     */
    public void setModel(String m) {
        this.model = m;
    }

    /**
     * Setter for this.color
     * @param c
     */
    public void setColor(String c) {
        this.color = c;
    }

    /**
     * Setter for this.licenseNum
     * @param l
     */
    public void setLicense(String l) {
        this.licenseNum = l;
    }

    /**
     * Setter for this.minsParked
     * @param n
     */
    public void setMinsParked(int n) {
        this.minsParked = n;
    }

    /**
     * Getter
     * @return this.make
     */
    public String getMake() {
        return this.make;
    }

    /**
     * Getter
     * @return this.model
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Getter
     * @return this.color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Getter
     * @return this.licenseNum
     */
    public String getLicense() {
        return this.licenseNum;
    }

    /**
     * Getter
     * @return this.minsParked
     */
    public int getMinsParked() {
        return this.minsParked;
    }
}
