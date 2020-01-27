package pizzaorder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Stephen Gerkin
 * @date 2019-08-12
 * Refresher Project
 * Pizza ordering application to simulate ordering a pizza
 */
public class PizzaOrder {

    private static ArrayList<Crust> crustOptions;
    private static ArrayList<Topping> meatOptions;
    private static ArrayList<Topping> vegetableOptions;

    private static Pizza pizza;

    public static final Double SALES_TAX_PERCENT = 0.06;

    private static final Scanner kb = new Scanner(System.in);

    /**
     * Driver
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initOptions();
        buildPizza();
        displayOrder();
    }

    /**
     * Initializes the options available for the pizza
     */
    private static void initOptions() {
        crustOptions = new ArrayList<Crust>() {
            {
                add(new Crust("Thin"));
                add(new Crust("Regular"));
                add(new Crust("Pan"));
            }
        };

        final String meatLbl = "Meat";
        meatOptions = new ArrayList<Topping>() {
            {
                add(new Topping(meatLbl, "Pepperoni"));
                add(new Topping(meatLbl, "Sausage"));
                add(new Topping(meatLbl, "Bacon"));
                add(new Topping(meatLbl, "Chicken"));
            }
        };

        final String veggieLbl = "Veggies";
        vegetableOptions = new ArrayList<Topping>() {
            {
                add(new Topping(veggieLbl, "Onion"));
                add(new Topping(veggieLbl, "Green Peppers"));
                add(new Topping(veggieLbl, "Broccoli"));
            }
        };
    }

    /**
     * Method to build a Pizza object
     */
    public static void buildPizza() {
        Crust crustSelection;
        ArrayList<Topping> toppingSelections = new ArrayList<>();

        crustSelection = getCrust();
        toppingSelections.addAll(getToppings(meatOptions));
        toppingSelections.addAll(getToppings(vegetableOptions));

        pizza = new Pizza(crustSelection, toppingSelections);
    }

    /**
     * Gets crust selection from user
     * @return Crust object from user selection
     */
    private static Crust getCrust() {
        System.out.println(
                "Please select a crust type:"
        );
        for (int i = 0; i < crustOptions.size(); i++) {
            System.out.println(
                (i+1) + ". " + crustOptions.get(i)
            );
        }
        int selection = getNumberSelection(crustOptions.size());

        return crustOptions.get(selection - 1);
    }

    /**
     * Gets topping selections from an ArrayList of options
     * @param options Available toppings to choose
     * @return List of selected toppings
     */
    public static ArrayList<Topping> getToppings(ArrayList<Topping> options) {
        ArrayList<Topping> selections = new ArrayList<>();

        boolean getMoreToppings = true;
        while (getMoreToppings) {
            System.out.println(
                "Select your " + options.get(0).getType() + " toppings:"
            );

            // display options
            int index;
            for (index = 0; index < options.size(); index++) {
                System.out.println(
                        (index + 1) + ": " + options.get(index)
                );
            }

            // add option for no selection of this type
            index++;
            System.out.println(
                index + ": None"
            );

            // get selection
            Integer selection = getNumberSelection(index);

            if (selection.equals(index)) { // determine if None selected
                getMoreToppings = false;
                break;
            }
            else { // o/w add it to the list of selections
                selections.add(options.get(selection - 1));
            }

            // check if user wants to add more toppings of this type
            getMoreToppings = queryGetMoreToppings();
        }
        return selections;
    }

    /**
     * Gets input from user and parses the input converting it to an integer
     * @param maxSelections Maximum allowable integer for menu selections
     * @return user input as a valid integer
     */
    private static Integer getNumberSelection(Integer maxSelections) {
        String userIn = kb.nextLine();
        Integer selection = -1;
        try {
            selection = Integer.parseInt(userIn);
            if (selection > maxSelections || selection < 1) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            System.err.println(
                "Invalid selection. Please make a valid selection from the menu."
            );
            return getNumberSelection(maxSelections);
        }
        return selection;
    }

    /**
     * Checks if user wants to enter more toppings
     * @return true if yes
     */
    private static boolean queryGetMoreToppings() {
        System.out.println(
                "Do you want to add more toppings? (y/n)"
        );

        Character userIn = (kb.nextLine()).charAt(0);

        if (userIn.equals('y')) {
            return true;
        }
        else if (userIn.equals('n')) {
            return false;
        }
        else {
            System.err.println(
                "Invalid selection. Please enter only 'y' or 'n'"
            );
            return queryGetMoreToppings();
        }
    }

    /**
     * Displays built pizza with crust, toppings, and overall cost
     */
    private static void displayOrder() {
        Double basePizzaCost = pizza.getCost();
        Double taxAmount = basePizzaCost * SALES_TAX_PERCENT;
        pizza.printPizza();

        System.out.printf("Order total: $%.2f\n", basePizzaCost);
        System.out.printf("Sales tax: $%.2f\n", taxAmount);
        System.out.printf("Total: $%.2f", (basePizzaCost + taxAmount));
    }
}
