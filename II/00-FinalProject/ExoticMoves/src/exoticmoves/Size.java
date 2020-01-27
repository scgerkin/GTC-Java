package exoticmoves;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * Debugging class for printing the sizes of GUI elements
 * Class has been mostly deprecated and removed from the program but was used
 * while determining values of various elements while setting up the layout of
 * the GUI
 */
public class Size {
    double width;
    double height;

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static void printSize(String name, Region region) {
        try {
            System.out.println(name + " width: " + region.getWidth());
            System.out.println(name + " height: " + region.getHeight());
        }
        catch (NullPointerException exception) {
            System.err.println(name + " is null");
        }
    }

    public static void printSize(String name, Window window) {
        try {
            System.out.println(name + " width: " + window.getWidth());
            System.out.println(name + " height: " + window.getHeight());
        }
        catch (NullPointerException exception) {
            System.err.println(name + " is null");
        }
    }

    public static void printSize(String name, Scene scene) {
        try {
            System.out.println(name + " width: " + scene.getWidth());
            System.out.println(name + " height: " + scene.getHeight());
        }
        catch (NullPointerException exception) {
            System.err.println(name + " is null");
        }
    }
}
