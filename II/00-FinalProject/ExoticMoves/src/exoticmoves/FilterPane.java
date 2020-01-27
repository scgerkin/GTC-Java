package exoticmoves;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FilterPane holds and controls the portion of GUI that displays the options
 * to filter the list of cars displayed
 */
public class FilterPane extends DisplayArea {
    // layout objects for the different filters
    private GridPane root;
    private VBox brandContainer;
    private VBox colorContainer;
    private VBox priceContainer;
    private VBox topTypeContainer;

    // objects for interacting with the rest of the program
    private Filters filters;
    private ListPane list;

    // list objects for available selection criteria
    private ArrayList<String> availableBrands;
    private ArrayList<String> availableColors;

    /**
     * Constructor
     * @param width of pane as pixels
     * @param height of pane as pixels
     * @param brands list of available brands
     * @param colors list of avaiable colors
     * @param filters Filters object used by program
     * @param listPane ListPane object used to display list of cars
     */
    public FilterPane(Double width, Double height, ArrayList<String> brands,
                      ArrayList<String> colors, Filters filters,
                      ListPane listPane)
    {
        super(width, height);
        this.availableBrands = brands;
        this.availableColors = colors;
        this.filters = filters;
        this.list = listPane;
        initialize();
    }

    /**
     * initializes container objects before building and displaying
     */
    private void initialize() {
        root = new GridPane();
        brandContainer = new VBox();
        colorContainer = new VBox();
        priceContainer = new VBox();
        topTypeContainer = new VBox();

        buildDisplay();
        super.setContainer(root);
        super.setCssClass("filterPane");
    }

    /**
     * builds and displays the filter GUI pane
     */
    private void buildDisplay() {
        Label label = new Label("Select your criteria");
        label.setStyle("-fx-font-weight: 700;");

        createFilterPanes();
        root.setVgap(10);
        root.add(label, 0, 0);
        root.add(brandContainer, 0, 1);
        root.add(colorContainer, 0, 2);
        root.add(priceContainer, 0, 3);
        root.add(topTypeContainer, 0, 4);
    }

    /**
     * calls methods to build each individual pane portion
     */
    private void createFilterPanes() {
        createBrandPane();
        createColorPane();
        createPricePane();
        createTopTypePane();
    }

    /**
     * creates pane for filtering by brand
     */
    private void createBrandPane() {
        Label label = new Label("Brand");
        ObservableList<CheckBox> options = FXCollections.observableArrayList();

        // create checkboxes
        for (String brand : availableBrands) {
            CheckBox optionItem = new CheckBox(brand);
            options.add(optionItem);

            // add event
            optionItem.setOnAction(event -> {
                if (optionItem.isSelected()) {
                    filters.brands.add(brand);
                }
                else {
                    filters.brands.remove(brand);
                }
                // refresh the display list after brand has been added/removed
                list.refreshDisplayList();
            });
        }

        // button to clear checkboxes
        // iterates through each box, and if a checkbox is selected, fires the
        // checkbox's event which unselects the checkbox and updates the filters
        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            for (CheckBox cb : options) {
                if (cb.isSelected()) {
                    cb.fire();
                }
            }
        });

        // box up elements into the brandContainer
        brandContainer.getChildren().add(label);
        brandContainer.getChildren().addAll(options);
        brandContainer.getChildren().add(clear);
    }

    /**
     * creates pane for filtering by color
     */
    private void createColorPane() {
        // create combo box with available colors
        ComboBox<String> optionList = new ComboBox<>();
        optionList.getItems().add("All Colors");
        optionList.getItems().addAll(availableColors);

        // set starting display value
        optionList.setValue("Available Colors");

        // add handler
        optionList.setOnAction(event -> {
            // set filter to option selected
            filters.color = optionList.getValue();

            // if All Colors selected, remove filter by setting to null
            if (filters.color.equals("All Colors")) {
                filters.color = null;
            }

            // refresh display
            list.refreshDisplayList();
        });

        // box up elements into the colorContainer
        colorContainer.getChildren().add(optionList);
    }

    /**
     * creates pane for filtering by price
     */
    private void createPricePane() {
        // create elements for input
        Label label = new Label("Price Range");

        CheckBox checkBoxEnable = new CheckBox();
        checkBoxEnable.setSelected(false);

        Label minInputLbl = new Label("From:");

        TextField minInField = new TextField();
        minInField.setDisable(true);

        Label maxInLbl = new Label("To:");

        TextField maxInField = new TextField();
        maxInField.setDisable(true);

        // text to notify user of invalid input
        Text badInputWarning = new Text("Please enter a valid value");
        badInputWarning.setVisible(false);

        Button updateButton = new Button("Update");//dummy to remove focus only
        Button clearButton = new Button("Clear");
        HBox buttonBox = new HBox(updateButton,clearButton);

        /* Event handlers follow */

        // handle input for minIn
        // changes filter when TextField loses focus
        minInField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            try {
                // get the value in the box
                int input = Integer.parseInt(minInField.getText());

                // if input is higher than the max filter price, throw exception
                if (input > filters.maxPrice) {
                    throw new NumberFormatException();
                }
                else {
                    // remove warning for invalid input if present
                    badInputWarning.setVisible(false);
                    // and set the minimum price
                    filters.minPrice = input;
                }
            }
            catch (NumberFormatException exception) {
                // display warning for invalid input
                badInputWarning.setVisible(true);

                // update the text in the text box to reflect actual filter
                // value being used
                if (filters.minPrice == Integer.MIN_VALUE) {
                    minInField.setText("");
                }
                else {
                    minInField.setText(Integer.toString(filters.minPrice));
                }
            }

            // refresh the display list
            list.refreshDisplayList();
        });

        // handle input for maxIn
        // works the same way as event above but for max filter value
        maxInField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            try {
                int input = Integer.parseInt(maxInField.getText());
                if (input < filters.minPrice) {
                    throw new NumberFormatException();
                }
                badInputWarning.setVisible(false);
                filters.maxPrice = input;
            }
            catch (NumberFormatException exception) {
                badInputWarning.setVisible(true);
                if (filters.maxPrice == Integer.MAX_VALUE) {
                    maxInField.setText("");
                }
                else {
                    maxInField.setText(Integer.toString(filters.maxPrice));
                }
            }
            list.refreshDisplayList();
        });

        // add handler for enabling filter by price
        checkBoxEnable.setOnAction(event -> {
            Boolean checked = checkBoxEnable.isSelected();

            if (checked) {
                try {
                    // set filter values to input values
                    filters.minPrice = Integer.parseInt(minInField.getText());
                    filters.maxPrice = Integer.parseInt(maxInField.getText());
                }
                catch (NumberFormatException exception){
                    // enabling for the first time can cause exception with null
                    // values, however this does not mess with the logic of
                    // the filters
                }
            }
            else {
                // otherwise "remove" filters
                filters.minPrice = Integer.MIN_VALUE;
                filters.maxPrice = Integer.MAX_VALUE;
            }
            // enable text fields if checked (disable if not checked)
            minInField.setDisable(!checked);
            maxInField.setDisable(!checked);
            // refresh list
            list.refreshDisplayList();
        });

        // clear button clears the price text in the boxes, resets min/max price
        // and refreshes the display list
        clearButton.setOnAction(event -> {
            filters.minPrice = Integer.MIN_VALUE;
            filters.maxPrice = Integer.MAX_VALUE;
            minInField.setText(null);
            maxInField.setText(null);
            list.refreshDisplayList();
        });

        // box up
        priceContainer.getChildren().addAll(
            label, checkBoxEnable, minInputLbl, minInField, maxInLbl,
            maxInField, badInputWarning, buttonBox
        );
    }

    /**
     * creates pane for filtering by top type (Convertible/Hard top)
     */
    private void createTopTypePane() {
        Label label = new Label("Top");

        // create toggle group and buttons
        ToggleGroup tg = new ToggleGroup();
        RadioButton allTops = new RadioButton("All");
        allTops.setToggleGroup(tg);
        RadioButton hardTop = new RadioButton("Fixed");
        hardTop.setToggleGroup(tg);
        RadioButton softTop = new RadioButton("Convertible");
        softTop.setToggleGroup(tg);

        // set All Top types as default starting value
        allTops.setSelected(true);
        tg.selectToggle(allTops);

        // add handler to toggle selections
        tg.selectedToggleProperty().addListener(e -> {
            // get selected button
            RadioButton rb = (RadioButton)tg.getSelectedToggle();

            // handle that button
            rb.setOnAction(tge -> {
                if (rb.equals(allTops)) {
                    filters.convertible = null;
                }
                else {
                    filters.convertible = rb.equals(softTop);
                }

                // refresh list
                list.refreshDisplayList();
            });
        });

        // box up elements
        topTypeContainer.getChildren().addAll(label, allTops, hardTop, softTop);
    }
}
