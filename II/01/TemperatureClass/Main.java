package temperatureclass;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *  @author Stephen Gerkin
 *  @date 2019-05-15
 *  Programming Lab 2 - 8. Temperature Class
 *  Main class to demonstrate the Temperature Class
 */
public class Main {

    // class I/O objs
    private static DecimalFormat fahrenheit = new DecimalFormat("###.00°F");
    private static DecimalFormat celsius = new DecimalFormat("###.00°C");
    private static DecimalFormat kelvin = new DecimalFormat("###.00K");
    private static Scanner kb = new Scanner(System.in);

    /**
     *  Main method demonstrates Temperature class
     *  @param args command line args
     */
    public static void main(String[] args) {
        // get user input for temp
        double userTemperature = getTemperature();

        // instantiate Temperature demo class with user input
        Temperature demo = new Temperature(userTemperature);

        System.out.println(
            "The temperature you entered was: "
            + fahrenheit.format(demo.getFahrenheit()) + ".\n"
            + "The temperature as Celsius is: "
            + celsius.format(demo.getCelsius()) + ".\n"
            + "The temperature as Kelvin is: "
            + kelvin.format(demo.getKelvin()) + ".\n"
        );

        // get new temp from user and use class mutator method
        System.out.println("Now change the temperature.");
        userTemperature = getTemperature();
        demo.setFahrenheit(userTemperature);

        System.out.println(
            "The temperature for the class is now:\n"
            + fahrenheit.format(demo.getFahrenheit()) + ".\n"
            + celsius.format(demo.getCelsius())+ ".\n"
            + kelvin.format(demo.getKelvin()) + "."
        );

        System.exit(0);
    }

    /**
     * Helper method to get temperature input
     * @return double   Temperature input
     */
    public static double getTemperature() {
        System.out.println("Enter the temperature in Fahrenheit:");

        double userInput = Double.parseDouble(kb.nextLine());

        return userInput;
    }
}
