package exoticmoves;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Stephen Gerkin
 * @date 2019-07-08
 * Final Project - Exotic Moves
 * GUI program to allow a user to view an inventory of cars, apply filters, and
 * purchase a car.
 */
public class ExoticMoves extends Application {

    // Inventory and Filter objects used by program
    private Inventory inventory;
    private Filters filters;

    // GUI Nodes, indentation indicates lineage
    private Stage stage;
        private Scene scene;
            private BorderPane borderPaneRoot;
                private Pane filterPaneRoot;
                private Pane listPaneRoot;
                    private ListPane listPane;
                private Pane dynamicPaneRoot;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        inventory = new Inventory();
        filters = new Filters();
        initStage(stage);
        buildAndDisplay();
    }

    /**
     * Sets the stage
     * @param s
     */
    private void initStage(Stage s) {
        stage = s;
        stage.setWidth(705);
        stage.setHeight(990);
        this.stage.setTitle("Exotic Moves");
        this.stage.setResizable(false);
    }

    /**
     * Builds the individual GUI elements and displays them
     */
    private void buildAndDisplay() {
        borderPaneRoot = new BorderPane();
        borderPaneRoot.getStyleClass().add("universal");

        initLogoDisplay();
        initScrollPaneDisplay();
        initFilterDisplay();

        scene = new Scene(borderPaneRoot);

        //add stylesheets
        loadStyleSheets();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method loads style sheets
     */
    private void loadStyleSheets() {
        scene.getStylesheets().add(
            "https://fonts.googleapis.com/css?family=Russo+One"
        );

        // apparently there's an issue with Java and loading Stylesheets rarely
        // I somehow found that rare case
        // this method makes it work 100% of the time
        File cssFile = new File("css/styleSheet.css");
        try {
            String cssFileLoc = cssFile.toURI().toURL().toString();
            scene.getStylesheets().add(cssFileLoc);
        }
        catch (MalformedURLException exception) {
            System.err.println("Problem opening css file");
        }
    }

    /**
     * Builds the logo display
     */
    private void initLogoDisplay() {
        LogoPane logo = new LogoPane(700d,100d);
        borderPaneRoot.setTop(logo.getPane());
    }

    /**
     * Builds the list display of cars
     */
    private void initScrollPaneDisplay() {
        listPane  = new ListPane(
            498d,860d,inventory.getAllCars(), new ListClickEvent(), filters
        );
        listPaneRoot = listPane.getPane();
        borderPaneRoot.setCenter(listPaneRoot);
    }

    /**
     * Event handler for list items
     */
    class ListClickEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Node source = (Node)event.getSource();
            String id = source.getId();
            displayCarDetails(inventory.getCarById(id));
        }
    }

    /**
     * Method to display an individual car details used by handler above
     * @param car
     */
    private void displayCarDetails(Car car) {
        // remove filterpane and listpane
        borderPaneRoot.getChildren().remove(filterPaneRoot);
        borderPaneRoot.getChildren().remove(listPaneRoot);

        // create display and put in root
        PurchasePane dynamicPane = new PurchasePane(
            borderPaneRoot.getWidth(), (borderPaneRoot.getHeight()-100), car,
            inventory, new CloseDisplayHandler()
        );
        dynamicPaneRoot = dynamicPane.getPane();

        // display
        borderPaneRoot.setCenter(dynamicPaneRoot);
    }

    /**
     * Handler for closing a detailed display
     */
    class CloseDisplayHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            borderPaneRoot.getChildren().remove(dynamicPaneRoot);
            borderPaneRoot.setLeft(filterPaneRoot);
            borderPaneRoot.setCenter(listPaneRoot);
            listPane.refreshDisplayList();
        }
    }

    /**
     * Builds the filter display
     */
    private void initFilterDisplay() {
        FilterPane filterPane = new FilterPane(
            200d,400d,inventory.getBrands(),inventory.getColors(),
            filters, listPane);
        filterPaneRoot = filterPane.getPane();
        borderPaneRoot.setLeft(filterPaneRoot);
    }
}
