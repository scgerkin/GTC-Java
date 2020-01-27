package pizzaorder;

import java.util.ArrayList;

/**
 * Pizza class aggregates ingredients for building a pizza and provides a method
 * to get the cost of the pizza and display the ingredients of the pizza
 */
public class Pizza {
    private Crust crust;
    private ArrayList<Topping> toppings;

    public Pizza(Crust crust, ArrayList<Topping> toppings) {
        this.crust = crust;
        this.toppings = toppings;
    }

    public void removeTopping(Topping topping) {
        if (toppings.contains(topping)) {
            toppings.remove(topping);
        } else {
            System.err.println(
                    "Error: " + topping.getName() + " not present on pizza."
            );
        }
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double getCost() {
        double cost = 5.00;
        for (Topping topping : toppings) {
            cost += topping.getCost();
        }
        return cost;
    }

    public void printPizza() {
        System.out.println(
            "Your pizza consists of the following:\n" +
            "Crust: " + crust.getName() + "\n" +
            "Toppings:"
        );

        if (toppings.isEmpty()) {
            System.out.println("Only cheese.");
        }
        else {
            for (Topping topping : toppings) {
                System.out.println(
                    topping.getName()
                );
            }
        }
    }
}
