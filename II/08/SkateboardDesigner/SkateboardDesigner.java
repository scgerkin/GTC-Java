package skateboarddesigner;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Stephen Gerkin
 * @date 2019-06-12
 * Programming Lab 9 - Skateboard Designer
 * Program provides a GUI allowing construction of a skateboard based on various
 * categories of items and selections of those items. Displays running total
 * cost of constructing the skateboard with selections.
 */
public class SkateboardDesigner extends Application {

    // DecimalFormat object to display double prices as USD
    private static final DecimalFormat df = new DecimalFormat("$###,##0.00");

    // member vars to hold sales tax and running subtotal of selections
    private static final double SALES_TAX = 0.06;
    private double subtotal = 0.00;

    // member var for vertical spacing between GUI objects
    private static final int space = 10;

    // Stage object for GUI
    private Stage mainStage;

    // Label objects for cost display
    private Label subLbl;
    private Label taxLbl;
    private Label totalLbl;

    // ArrayList to hold the Category objects for the store
    private ArrayList<Category> categories;

    /**
     * Sets up and displays GUI
     */
    @Override
    public void start(Stage primaryStage) {
        initStage(primaryStage);

        VBox display = new VBox(space);

        createCategories();

        HBox top = createSelectionMenus();
        VBox bottom = createCostDisplay();
        bottom.setAlignment(Pos.CENTER);

        display.getChildren().add(top);
        display.getChildren().add(bottom);

        setScene(new Scene(display));
    }

    /**
     * Creates the GUI elements for user selections
     * The ComboBox selections will be on the left and ListView on the right
     * @return
     */
    private HBox createSelectionMenus() {
        VBox left = new VBox(space);
        VBox right = new VBox(space);

        // iterate through categories and add to left or right
        // Single selection categories on left as a ComboBox
        // Multi-selection categories on right as ListView
        for (int i = 0; i < this.categories.size(); i++) {
            if (!this.categories.get(i).multipleAllowed()) {
                left.getChildren().add(
                    createSingleSelectMenu(
                        this.categories.get(i)
                    )
                );
            }
            else {
                right.getChildren().add(
                    createMultiSelectMenu(
                        this.categories.get(i)
                    )
                );
            }
        }

        return new HBox(space, left, right);
    }

    /**
     * Sets the stage created by JavaFX to this.mainStage and initializes the
     * stage attributes
     * @param primaryStage
     */
    private void initStage(Stage primaryStage) {
        this.mainStage = primaryStage;
        mainStage.setTitle("Skateboard Designer");
        mainStage.setHeight(310);
        mainStage.setWidth(400);
        mainStage.setResizable(true);
    }

    /**
     * Creates the categories used by the shop and populates them
     * TODO: Currently hardcoded, future plan to implement reading values from
     * a JSON or CSV file
     */
    private void createCategories() {
        this.categories = new ArrayList<>();

        Category decks = new Category("Decks");
        decks.addItem(new Item("The Master Thrasher", 60.00));
        decks.addItem(new Item("The Dictator", 45.00));
        decks.addItem(new Item("The Street King", 50.00));

        Category trucks = new Category("Truck Assemblies");
        trucks.addItem(new Item("7.75\" axle", 35.00));
        trucks.addItem(new Item("8\" axle", 40.00));
        trucks.addItem(new Item("8.5\" axle", 45.00));

        Category wheels = new Category("Wheels");
        wheels.addItem("51mm", 20.00);
        wheels.addItem("55mm", 22.00);
        wheels.addItem("58mm", 24.00);
        wheels.addItem("61mm", 28.00);

        Category misc = new Category("Miscellaneous Products");
        misc.allowMultiple(true);
        misc.addItem("Grip Tape", 10.00);
        misc.addItem("Bearings", 30.00);
        misc.addItem("Riser Pads", 2.00);
        misc.addItem("Nuts & Bolts Kit", 3.00);

        this.categories.add(decks);
        this.categories.add(trucks);
        this.categories.add(wheels);
        this.categories.add(misc);
    }

    /**
     * Creates a ComboBox menu for selecting an item from the Category object
     * it receives
     * @param category
     * @return VBox containing the menu items
     */
    private VBox createSingleSelectMenu(Category category) {
        // create a Label and ComboBox for the menu and set the attributes
        Label menuLabel = new Label(category.getName());
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setMinSize(200, 0);

        // get a list of the items to add to the ComboBox
        ArrayList<Item> items = category.getItems();

        // create nodes and add to ComboBox object
        for (Item i : items) {
            comboBox.getItems().add(i.toString());
        }

        // add event handling
        comboBox.setOnAction(event -> {
            String selection = comboBox.getValue();

            category.setCost(selection);

            updateCost();
        });

        // gather and return
        return new VBox(space, menuLabel, comboBox);
    }

    /**
     * Creates a ListView menu and a button for selecting items from it from the
     * Category object it receives
     * @param category
     * @return VBox containing the menu items
     */
    private VBox createMultiSelectMenu(Category category) {
        // create a menu Label and ListView object and set attributes
        Label menuLabel = new Label(category.getName());
        ListView<String> listView = new ListView<>();
        listView.setPrefSize(100,100);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setMinSize(150, 50);

        // get the items to add to the menu
        ArrayList<Item> items = category.getItems();

        // add the items to the menu
        for (Item i : items) {
            listView.getItems().add(i.toString());
        }

        // make a button for updating total
        Button update = new Button("Add Selections");

        // add event listener to the button
        update.setOnAction(e -> {
            // get selections
            ObservableList<String> selections =
                listView.getSelectionModel().getSelectedItems();

            // temp var to accumulate total
            double cost = 0.0;

            // accumulate total
            for (String s : selections) {
                category.setCost(s);
                cost += category.getCost();
            }

            // set category cost to accumulated cost
            category.setCost(cost);

            updateCost();
        });

        // gather and return
        return new VBox(space, menuLabel, listView, update);
    }

    /**
     * Creates the Label objects for displaying running cost
     * @return VBox containing the cost display Label objects
     */
    private VBox createCostDisplay() {
        // create labels
        this.subLbl = new Label();
        this.taxLbl = new Label();
        this.totalLbl = new Label();

        // update the display window
        updateCostDisplay();

        // gather and return
        VBox box = new VBox(space, subLbl, taxLbl, totalLbl);

        return box;
    }

    /**
     * Updates the running total cost then calls method to update the display
     */
    private void updateCost() {
        double temp = 0.0;
        for (Category category : this.categories) {
            temp += category.getCost();
        }
        this.subtotal = temp;

        updateCostDisplay();
    }

    /**
     * Updates the cost display labels
     */
    private void updateCostDisplay() {
        double tax = subtotal * SALES_TAX;

        subLbl.setText("Sub-Total: " + df.format(subtotal));
        taxLbl.setText("Sales Tax: " + df.format(tax));
        totalLbl.setText("Order Total: " + df.format(subtotal + tax));
        totalLbl.setStyle("-fx-font-weight: 700;");
    }

    /**
     * Displays a new scene
     * @param scene to display
     */
    private void setScene(Scene scene) {
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Main method to launch GUI with JavaFX
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
