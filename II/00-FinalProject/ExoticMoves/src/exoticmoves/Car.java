package exoticmoves;

import javafx.scene.image.Image;

/**
 * Car class holds information about a car
 */
public class Car {

    private String brand;
    private String color;
    private int price;
    private Boolean convertible;
    private int cylinders;
    private double performance;
    private Image image;
    private String uniqueId;

    /**
     * Default constructor
     * @param brand
     * @param color
     * @param price
     * @param convertible
     * @param cylinders
     * @param performance
     * @param image
     * @param uniqueId
     */
    public Car(String brand, String color, int price, Boolean convertible,
               int cylinders, double performance, Image image,
               String uniqueId)
    {
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.convertible = convertible;
        this.cylinders = cylinders;
        this.performance = performance;
        this.image = image;
        this.uniqueId = uniqueId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return (
            "Brand: " + brand + "\n" +
            "Color: " + color + "\n" +
            "Convertible: " + ((convertible) ? "Yes" : "No") + "\n" +
            "Price: $" + price + ",000\n" +
            "Cylinders: " + cylinders + "\n" +
            "0-60 Performance: " + performance + " secs"
        );
    }
}
