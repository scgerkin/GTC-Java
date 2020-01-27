package exoticmoves;

import java.util.ArrayList;

/**
 * Filters class holds current criteria to filter the displayed list by
 */
public class Filters {
    ArrayList<String> brands;
    String color;
    int minPrice;
    int maxPrice;
    Boolean convertible;

    public Filters() {
        brands = new ArrayList<>();
        color = null;
        minPrice = Integer.MIN_VALUE;
        maxPrice = Integer.MAX_VALUE;
        convertible = null;
    }

    /**
     * Method checks if a car is allowed by current filters
     * @param car
     * @return
     */
    public Boolean allow(Car car) {
        if (!brands.isEmpty() && !brands.contains(car.getBrand())) {
            return false;
        }
        if (color != null && !color.equals(car.getColor())) {
            return false;
        }
        if (minPrice > car.getPrice() || maxPrice < car.getPrice()) {
            return false;
        }
        if (convertible != null) {
            return convertible == car.isConvertible();
        }
        // if we get here, car passes filters
        return true;
    }
}
