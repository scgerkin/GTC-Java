/*
 * ParkedCar class definition
 */
package lesson13lab12program2;

/**
 *  ParkedCar class creates an object for storing information about a parked car
 */
public class ParkedCar {
    
    // instance variables
    private String make;
    private String model;
    private String color;
    private String licenseNum;
    private int minsParked;
    
    
    /**
     * Default no argument constructor
     */
    public ParkedCar() {
        make = "";
        model = "";
        color = "";
        licenseNum = "";
        minsParked = 0;
    }
    
    /**
     * Full argument Constructor
     * @param make
     * @param model
     * @param color
     * @param licenseNum
     * @param minsParked 
     */
    public ParkedCar(String make, String model,
                     String color, String licenseNum, int minsParked) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.licenseNum = licenseNum;
        this.minsParked = minsParked;
    }
    
    /**
     * copy constructor
     * @param carObj    object to copy
     */
    public ParkedCar(ParkedCar carObj) {
        this.make = carObj.make;
        this.model = carObj.model;
        this.color = carObj.color;
        this.licenseNum = carObj.licenseNum;
        this.minsParked = carObj.minsParked;
    }
    
    /**
     * mutator to set make
     * @param str 
     */
    public void setMake(String str) {
        make = str;
    }
    
    /**
     * mutator to set model
     * @param str 
     */
    public void setModel(String str) {
        model = str;
    }
    
    /**
     * mutator to set color
     * @param str 
     */
    public void setColor(String str) {
        color = str;
    }
    
    /**
     * mutator to set license number
     * @param str 
     */
    public void setLicenseNum(String str) {
        licenseNum = str;
    }
    
    /**
     * mutator to set minutes parked
     * @param mins 
     */
    public void setMinsParked(int mins) {
        minsParked = mins;
    }
    
    /**
     * getter for make
     * @return 
     */
    public String getMake() {
        return make;
    }
    
    /**
     * getter for model
     * @return 
     */
    public String getModel() {
        return model;
    }
    
    /**
     * getter for color
     * @return 
     */
    public String getColor() {
        return color;
    }
    
    /**
     * getter for license number
     * @return 
     */
    public String getLicense() {
        return licenseNum;
    }
    
    /**
     * getter for minutes parked
     * @return 
     */
    public int getMinsParked() {
        return minsParked;
    }
}