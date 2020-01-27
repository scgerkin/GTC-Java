package cellphonepackages;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Category class holds Items that are sold by the shop.
 * Provides methods to set the cost of the Category based on an EventHandler
 * using either the String returned by an EventHandler or an accumulated cost of
 * Items
 * Provides methods to get list of Items in Category
 */
public class Category {

    private String name;
    private ArrayList<Item> items;
    private Boolean multipleAllowed;
    private Double cost;

    /**
     * Constructor without a list of Items provided
     * @param categoryName
     */
    Category(String categoryName) {
        this.name = categoryName;
        this.items = new ArrayList<>();
        this.multipleAllowed = false;
        this.cost = 0.0;
    }

    /**
     * Constructor with a list Items provided
     * @param categoryName
     * @param list
     */
    Category(String categoryName, ArrayList<Item> list) {
        this(categoryName);

        // make a deep copy
        for (Item item : list) {
            this.items.add(item);
        }
    }

    /**
     * Used to allow multiple selections (true) or only one selection (false)
     * @param multiple
     */
    public void allowMultiple(Boolean multiple) {
        this.multipleAllowed = multiple;
    }

    /**
     * @return true if multiple selections from this Category are allowed
     */
    public Boolean multipleAllowed() {
        return this.multipleAllowed;
    }

    /**
     * Adds a new Item obj to the ArrayList of Items held by this Category obj
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Overload of above to construct a new Item obj and add to ArrayList
     * @param itemName
     * @param itemPrice
     */
    public void addItem(String itemName, double itemPrice) {
        this.items.add(new Item(itemName, itemPrice));
    }

    /**
     * Getter
     * @return Category name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter
     * @return ArrayList of Items held by this
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * @return current cost of selection(s). Needs to be set by calling class
     * with an EventHandler
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Used to set the cost of a currently selected item by comparing itemStr
     * to the Item.toString(). Used by EventHandlers that return selections
     * as a String value
     * @param itemStr
     */
    public void setCost(String itemStr) {
        if (itemStr == null) {
            this.cost = 0.0;
            return;
        }
        for (Item item : this.items) {
            if (item.getName().equals(itemStr)) {
                this.cost = item.getPrice();
                break;
            }
            else {
                this.cost = 0.0;
            }
        }
    }

    /**
     * Overload of above that sets currently selected cost to newCost
     * Used by a category that allows mutliple selections. Cost should be
     * accumulated by calling class from selections and call this method
     * @param newCost
     */
    public void setCost(Double newCost) {
        this.cost = newCost;
    }

    /**
     * Adds to this.cost. Used for multi-selectable menu items. Send in positive
     * value if the menu item is selected, otherwise send in value * -1
     * @param addCost
     */
    public void addCost(Double addCost) {
        this.cost += addCost;
    }

    /**
     * @return an ArrayList of the names of Items held by class
     */
    public ArrayList<String> getItemNames() {
        ArrayList<String> names = new ArrayList<>(this.items.size()-1);

        for (Item item : this.items) {
            names.add(item.getName());
        }

        return names;
    }

    /**
     * @return an ArrayList of the prices of Items held by class
     */
    public ArrayList<Double> getItemPrices() {
        ArrayList<Double> prices = new ArrayList<>(this.items.size()-1);

        for (Item item : this.items) {
            prices.add(item.getPrice());
        }

        return prices;
    }

    /**
     * Sorts list of Items held by class by name (ascending)
     */
    public void sortItemsByName() {
        Collections.sort(this.items);
    }

    /**
     * Sorts list of Items held by class by price (ascending)
     */
    public void sortItemsByPrice() {
        PriceCompare priceCompare = new PriceCompare();
        Collections.sort(this.items, priceCompare);
    }
}
