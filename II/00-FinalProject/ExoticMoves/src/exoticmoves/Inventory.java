package exoticmoves;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * Inventory class holds a list of cars in inventory, available brands, and
 * available colors. Provides methods for building the inventory from file
 * (Currently reads the provided image files and builds from that)
 * and methods for interacting with the inventory
 */
public class Inventory {
    private ArrayList<Car> carList;
    private ArrayList<String> brands;
    private ArrayList<String> colors;

    /**
     * Default constructor inits the list items and builds the inventory
     */
    public Inventory() {
        carList = new ArrayList<>();
        brands = new ArrayList<>();
        colors = new ArrayList<>();

        buildInventory();
    }

    /**
     * Method builds the inventory based off provided image file names
     * @throws NullPointerException if folder containing image files not found
     */
    private void buildInventory() throws NullPointerException {
        File folder = new File("cars\\");

        if (folder.listFiles() == null) {
            throw new NullPointerException(
                "Folder containing car images is empty."
            );
        }

        // read each file name and build a car based off it
        for (File file : folder.listFiles()) {
            // tokenize file name string
            String[] tokens = getTokens(file.getName());

            // get color
            String color = determineColor(tokens[0]);
            if (!colors.contains(color)) {
                colors.add(color);
            }

            // get brand name
            String brand = determineBrand(tokens[1]);
            if (!brands.contains(brand)) {
                brands.add(brand);
            }

            // determine if convertible
            Boolean convertible = tokens[tokens.length-1].equals("Convertible");

            // determine price, cylinders, and performance
            int price = 0;
            int cylinders = 0;
            double performance = 0;

            try {
                price = determinePrice(brand);
                cylinders = determineCylinders(brand);
                performance = determinePerformance(brand);
            }
            catch (IllegalArgumentException e) {
                price = -1;
                cylinders = -1;
                performance = -1;
                System.err.println("Problem building car from " + brand);
                System.err.println("Current file: " + file.getName());
            }

            // get image
            Image img;
            try {
                img = getImage(file.getPath());
            }
            catch (IOException e) {
                img = null;
                System.out.println(
                    String.format("Problem with file: ", file.getName())
                );
            }

            // add a new car to car list
            carList.add(
                new Car(
                    brand, color, price, convertible,
                    cylinders, performance, img,
                    //ID is current size of car list
                    Integer.toString(carList.size())
                )
            );
        }
    }

    /**
     * Method tokenizes @param s
     * @return string without file extension, split by upper case letters
     */
    private String[] getTokens(String s) {
        return s.substring(0, s.length()-4).split("(?=\\p{Upper})");
    }

    /**
     * Method converts file name strings to color name strings
     */
    private String determineColor(String s) {
        if (s.equals("Blk")) {
            return "Black";
        }
        if (s.equals("Blu")) {
            return "Blue";
        }
        return s;
    }

    /**
     * Method converts the file name strings to brand name strings
     */
    private String determineBrand(String s) {
        if (s.equals("Mc")) {
            return "McLaren";
        }
        if (s.equals("Aston")) {
            return "Aston Martin";
        }
        if (s.equals("Lambo")) {
            return "Lamborghini";
        }
        return s;
    }

    /**
     * Method to determine the price for @param brand
     * @throws IllegalArgumentException if brand not found
     */
    private int determinePrice(String brand) throws IllegalArgumentException {
        switch (brand) {
            case "Ferrari": return 200;
            case "Maserati": return 100;
            case "Aston Martin": return 120;
            case "McLaren": return 265;
            case "Lamborghini": return 400;
        }
        // if we get here the brand doesn't exist, throw exception
        throw new IllegalArgumentException("Brand not found");
    }

    /**
     * Method to determine the number of cylinders for @param brand
     * @throws IllegalArgumentException if brand not found
     */
    private int determineCylinders(String brand) throws IllegalArgumentException {
        switch (brand) {
            case "Ferrari": return 8;
            case "Maserati": return 6;
            case "Aston Martin": return 6;
            case "McLaren": return 8;
            case "Lamborghini": return 12;
        }
        // if we get here the brand doesn't exist, throw exception
        throw new IllegalArgumentException("Brand not found");
    }

    /**
     * Method to determine the performance for @param brand
     * @throws IllegalArgumentException if brand not found
     */
    private double determinePerformance(String brand) throws IllegalArgumentException {
        switch (brand) {
            case "Ferrari": return 2.7;
            case "Maserati": return 3.6;
            case "Aston Martin": return 3.8;
            case "McLaren": return 2.5;
            case "Lamborghini": return 2.5;
        }
        // if we get here the brand doesn't exist, throw exception
        throw new IllegalArgumentException("Brand not found");
    }

    /**
     * Method creates an Image object from a string using try (with) method that
     * automatically executes a finally clause to close any opened file streams
     * @param s File name for image
     * @return Image object
     * @throws IOException Problem opening file
     */
    private Image getImage(String s) throws IOException {
        try (FileInputStream fis2 = new FileInputStream(s)) {
            return new Image(fis2);
        }
    }

    /**
     * Getter for list of all cars in inventory
     */
    public ArrayList<Car> getAllCars() {
        return carList;
    }

    /**
     * Method to @return a car by @param id
     * @throws IllegalArgumentException if provided ID not found in inventory
     */
    public Car getCarById(String id) throws IllegalArgumentException {
        for (Car c : carList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("ID not found");
    }

    /**
     * Removes a car from the inventory by @param id
     * @throws IllegalArgumentException if provided ID not found in inventory
     */
    public void removeCarById(String id) throws IllegalArgumentException {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                removeCar(car);
                break;
            }
        }
        throw new IllegalArgumentException("ID not found");
    }

    /**
     * Removes a car from the inventory by providing @param car
     */
    public void removeCar(Car car) {
        carList.remove(car);
    }

    /**
     * Method to add a new @param car to the inventory
     */
    public void addCar(Car car) {
        carList.add(car);
        if (!colors.contains(car.getColor())) {
            colors.add(car.getColor());
        }
        if (!brands.contains(car.getBrand())) {
            brands.add(car.getBrand());
        }
    }

    /**
     * Getter for available brands list
     */
    public ArrayList<String> getBrands() {
        return brands;
    }

    /**
     * Getter for available colors list
     */
    public ArrayList<String> getColors() {
        return colors;
    }
}
