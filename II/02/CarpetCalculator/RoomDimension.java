package carpetcalculator;

/**
 * RoomDimension class stores the length and width of a room
 * Provides methods for getting total area
 */
public class RoomDimension {
    private double length;
    private double width;

    /**
     * Default constructor
     * @param roomLength
     * @param roomWidth
     */
    public RoomDimension(double roomLength, double roomWidth) {
        this.length = roomLength;
        this.width = roomWidth;
    }

    /**
     * Copy Constructor
     * @param obj
     */
    public RoomDimension(RoomDimension obj) {
        this.length = obj.getLength();
        this.width = obj.getWidth();
    }

    /**
     * Setter for this.length
     * @param roomLength
     */
    public void setLength(double roomLength) {
        this.length = roomLength;
    }

    /**
     * Setter for this.width
     * @param roomWidth
     */
    public void setWidth(double roomWidth) {
        this.length = roomWidth;
    }

    /**
     * Setter for this.length and this.width
     * @param roomLength
     * @param roomWidth
     */
    public void setDimensions(double roomLength, double roomWidth) {
        this.length = roomLength;
        this.width = roomWidth;
    }

    /**
     * Getter
     * @return this.length
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Getter
     * @return this.width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for area of Room
     * @return this.length * this.width
     */
    public double getArea() {
        return this.length * this.width;
    }

    /**
     * toString override
     */
    @Override
    public String toString() {
        String str = "Length: " + this.length + "\n"
                   + "Width: " + this.width + "\n"
                   + "Area: " + this.getArea();
        return str;
    }
}
