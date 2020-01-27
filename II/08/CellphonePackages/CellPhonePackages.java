package cellphonepackages;

import java.util.ArrayList;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author Stephen Gerkin
 * @date 2019-06-12
 * Programming Lab 9 - Cell Phone Packages
 * Application that displays a menu system for selecting cell phone options
 * and displays the individual costs of each selection
 */
public class CellPhonePackages extends Application {

    // decimal format for converting doubles into USD string
    private static final DecimalFormat df = new DecimalFormat("$###,##0.00");

    private final int space = 10;           // vertical spacing between elements
    private Stage mainStage;                // main stage used by GUI
    private ArrayList<Label> costLabels;    // array of labels to display costs
    private ArrayList<Category> categories; // array of categories user selects

    @Override
    public void start(Stage primaryStage) {
        initStage(primaryStage);

        createCategories();

        VBox root = createRootBox();

        setScene(new Scene(root));
    }

    /**
     * @return VBox containing the main display items
     */
    private VBox createRootBox() {
        VBox box = new VBox();

        // add the border pane
        box.getChildren().add(createBorderPane());

        // add the cost display
        box.getChildren().add(createCostDisplay());

        return box;
    }

    /**
     * @return BorderPane containing the Menus used by program
     */
    private BorderPane createBorderPane() {
        BorderPane borderPane = new BorderPane();

        // add the menu bar
        borderPane.setTop(createMenuBar());

        return borderPane;
    }

    /**
     * @return Top menu bar
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // add the File Menu
        menuBar.getMenus().add(createFileMenu());

        // add the category menus
        for (Category category: this.categories) {
            menuBar.getMenus().add(createMenu(category));
        }

        return menuBar;
    }

    /**
     * @return Menu for "File" options
     */
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("_File");
        MenuItem exitItem = new MenuItem("E_xit");

        // add exit item
        fileMenu.getItems().add(exitItem);

        // add event
        exitItem.setOnAction(e -> {
            this.mainStage.close();
        });

        return fileMenu;
    }

    /**
     * @param category
     * @return Menu obj from category
     */
    private Menu createMenu(Category category) {
        Menu menu = new Menu(category.getName());

        if (category.multipleAllowed()) {
            menu.getItems().addAll(createMultiSelect(category));
        }
        else {
            menu.getItems().addAll(createSingleSelect(category));
        }

        return menu;
    }

    /**
     * Creates a menu item from a category only one selection available in
     * parent Menu
     * @param category
     * @return
     */
    private ArrayList<MenuItem> createSingleSelect(Category category) {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (int i = 0; i < category.getItems().size(); i++) {
            MenuItem menuItem = new MenuItem(category.getItemNames().get(i));
            menuItems.add(menuItem);

            // add listeners
            menuItem.setOnAction(e -> {
                String selection = menuItem.getText();

                category.setCost(selection);
                updateCostDisplay();
            });
        }

        return menuItems;
    }

    /**
     * Creates a menu item from a category with multiple selections available
     * in parent Menu
     * @param cat
     * @return
     */
    private ArrayList<CheckMenuItem> createMultiSelect(Category cat) {
        ArrayList<CheckMenuItem> menuItems = new ArrayList<>();

        // iterate over items in the category
        for (int i = 0; i < cat.getItems().size(); i++) {
            // create a CheckMenuItem from the name of the current item
            CheckMenuItem cmi = new CheckMenuItem(cat.getItemNames().get(i));

            // add listener for the CheckMenuItem using the category and current
            // item
            cmi = createMultiEventHandler(cmi, cat, i);

            menuItems.add(cmi);
        }

        return menuItems;
    }

    /**
     * Creates an event handler for a CheckMenu Item that has a parent allowing
     * multiple selections
     * @param cmi
     * @param cat
     * @param index
     * @return
     */
    private CheckMenuItem createMultiEventHandler(CheckMenuItem cmi,
                                                  Category cat,
                                                  int index)
    {
        cmi.setOnAction(e -> {

            // if the CheckMenuItem is selected, add the item cost to Category
            if (cmi.isSelected()) {
                cat.addCost(cat.getItemPrices().get(index));
            }
            // otherwise subtract the item cost from the Category
            else {
                cat.addCost((cat.getItemPrices().get(index)) * -1.0);
            }

            updateCostDisplay();
        });

        return cmi;
    }

    /**
     * Creates the categories for user selection
     */
    private void createCategories() {
        this.categories = new ArrayList<>();

        Category packages = new Category("Packages");
        packages.addItem("300 minutes", 45.00);
        packages.addItem("800 minutes", 65.00);
        packages.addItem("1500 minutes", 99.00);

        Category phones = new Category("Phones");
        phones.addItem("Model 100", 29.95);
        phones.addItem("Model 110", 49.95);
        phones.addItem("Model 200", 99.95);

        Category options = new Category("Options");
        options.allowMultiple(true);
        options.addItem("Voice Mail", 5.00);
        options.addItem("Text Messaging", 10.00);

        categories.add(packages);
        categories.add(phones);
        categories.add(options);
    }

    /**
     * Creates and inits the cost display window
     * @return VBox container with this.costLabels
     */
    private VBox createCostDisplay() {
        this.costLabels = new ArrayList<>();
        VBox box = new VBox(space);

        for (int i = 0; i < categories.size(); i++) {
            costLabels.add(new Label());
        }

        box.getChildren().addAll(costLabels);

        updateCostDisplay();

        return box;

    }

    /**
     * Updates the text for the costLabel displayed
     */
    private void updateCostDisplay() {
        for (int i = 0; i < categories.size(); i++) {
            String costName = categories.get(i).getName();
            String costPrice = df.format(categories.get(i).getCost());
            costLabels.get(i).setText(costName + " Cost: " + costPrice);
        }
    }

    /**
     * Initializes the stage with attributes and binds this.mainStage to the
     * Stage created by JavaFX
     * @param primaryStage
     */
    private void initStage(Stage primaryStage) {
        this.mainStage = primaryStage;
        mainStage.setTitle("Cell Phone Packages");
        mainStage.setHeight(200);
        mainStage.setWidth(250);
        mainStage.setResizable(true);
    }

    /**
     * @param scene new scene to display
     */
    private void setScene(Scene scene) {
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
