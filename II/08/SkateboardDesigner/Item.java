package skateboarddesigner;

import java.text.DecimalFormat;
import java.util.Comparator;

/**
 * Item class holds name and price of an item and provides methods for
 * comparing items by name and price to easily sort a list of items
 */
public class Item implements Comparable<Item> {

    // Class DecimalFormat obj to return price as USD amount
    private static final DecimalFormat df = new DecimalFormat("$###,##0.00");

    private String name;
    private double price;

    public Item(String itemName, double itemPrice) {
        this.name = itemName;
        this.price = itemPrice;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    /**
     * Used to compare Item objects by name field
     * @param o second object to compare to
     */
    @Override
    public int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }

    /**
     * String representation of class will return the item name and price
     * using DecimalFormat to format price as String
     * e.g.
     * "Basketball: $5.00"
     */
    @Override
    public String toString() {
        return this.getName() + ": " + df.format(this.getPrice());
    }
}

/**
 * Used to compare Item objects by price
 * IE to sort by price:
 * PriceCompare priceCompare = new PriceCompare();
 * Collections.sort(itemList, priceCompare);
 *
 * This sorts an array list of Items by price instead of name
 */
class PriceCompare implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getPrice() < o2.getPrice()) {
            return -1;
        }
        else if (o1.getPrice() > o2.getPrice()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
