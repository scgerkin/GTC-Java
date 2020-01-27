package carpetcalculator;

import java.text.DecimalFormat;

/**
 * RoomCarpet class stores information for a room and associated carpeting cost
 * Provides methods to retrieve these fields
 */
public class RoomCarpet {
    private RoomDimension size;
    private double carpetCost;  // Cost of carpet per square foot

    /**
     * Default constructor
     * @param obj   RoomDimension object to be carpeted
     * @param cost  Cost of carpet per square foot
     */
    public RoomCarpet(RoomDimension obj, double cost) {
        this.size = new RoomDimension(obj);
        this.carpetCost = cost;
    }

    /**
     * Setter for this.size
     * @param obj   RoomDimension object
     */
    public void setSize(RoomDimension obj) {
        this.size = new RoomDimension(obj);
    }

    /**
     * Setter for this.cost
     */
    public void setCost(double cost) {
        this.carpetCost = cost;
    }

    /**
     * Getter for room dimensions
     * @return  RoomDimension obj as string
     */
    public String getDimensions() {
        return this.size.toString();
    }

    /**
     * Getter for total cost of carpeting the room
     * @return
     */
    public double getTotalCost() {
        return this.carpetCost * this.size.getArea();
    }

    /**
     * toString() override
     * NOTE: Returns cost as Dollars
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$###,###.00");
        String str = this.size.toString() + "\n"
                   + "Cost per square foot: " + df.format(this.carpetCost) + "\n"
                   + "Total cost: " + df.format(this.getTotalCost());
        return str;
    }
}
