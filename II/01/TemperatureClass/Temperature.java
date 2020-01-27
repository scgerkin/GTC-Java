package temperatureclass;

/**
 * Temperature Class
 * Holds temperature as Fahrenheit
 * Provides methods for accessing temperature as Fahrenheit, Celsius, and Kelvin
 */
public class Temperature {

    private double ftemp;

    /**
     * Default constructor
     * @param temp  Temperature as Fahrenheit
     */
    public Temperature(double temp) {
        this.ftemp = temp;
    }

    /**
     * Mutator for temperature
     * @param temp  Temperature as Fahrenheit
     */
    public void setFahrenheit(double temp) {
        this.ftemp = temp;
    }

    /**
     * Accessor for temperature as Fahrenheit
     * @return String   Temperature
     */
    public double getFahrenheit() {
        return this.ftemp;
    }

    /**
     * Accessor for temperature as Celsius
     * @return String   Temperature
     */
    public double getCelsius() {
        return (5f/9f) * (this.ftemp - 32f);
    }

    /**
     * Accessor for temperature as Kelvin
     * @return String   Temperature
     */
    public double getKelvin() {
        return ((5f/9f) * (this.ftemp - 32f)) + 273f;
    }
}
