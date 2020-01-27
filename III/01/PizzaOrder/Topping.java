package pizzaorder;

/**
 * Topping class stores information for a Pizza topping
 */
public class Topping extends Ingredient {
    private String type;
    private String name;

    public Topping(String toppingType, String toppingName) {
        super(toppingName, 0.25);
        this.type = toppingType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
