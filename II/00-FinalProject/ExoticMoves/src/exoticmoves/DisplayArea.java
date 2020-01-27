package exoticmoves;

import javafx.scene.layout.Pane;

/**
 * Parent class for containing GUI pane elements
 * Manages the size and css of the parental pane
 */
public abstract class DisplayArea {
    private Double width;
    private Double height;
    private Pane container;

    /**
     * Constructor with parameters for size
     * @param width
     * @param height
     */
    public DisplayArea(Double width, Double height) {
        this.width = width;
        this.height = height;
        container = new Pane();

        applyCurrentSize();
    }

    /**
     * Method to change the size of a display area
     * @param newWidth
     * @param newHeight
     */
    public void changeSize(Double newWidth, Double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
        applyCurrentSize();
    }

    /**
     * Method to set the CSS class for the display area
     * @param cssClass
     */
    public void setCssClass(String cssClass) {
        container.getStyleClass().add(cssClass);
    }

    /**
     * Used to set the outermost container Pane of a display area
     * @param child
     */
    public void setContainer(Pane child) {
        container = child;
        applyCurrentSize();
    }

    /**
     * Private method applies the stored width/height by setting the pane
     * min, max, and preferred size values. This allows for an absolute value
     * for standardizing between display areas and minimizing headaches
     */
    private void applyCurrentSize() {
        container.setMinSize(this.width, this.height);
        container.setPrefSize(this.width, this.height);
        container.setMaxSize(this.width, this.height);
    }

    /**
     * Method to return the parent Pane for the display area
     * @return
     */
    public Pane getPane() {
        return container;
    }

    /**
     * Debugging method to print the display area to console
     */
    public void printSize() {
        System.out.println(
            "Width: " + container.getWidth() +
            " Height: " + container.getHeight()
        );
    }
}
