package checkerboard;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 *
 * @author Stephen Gerkin
 * @date 2019-06-14
 * Programming Lab 10 - 5. Checkerboard
 * Program to display NxN Checkerboard with user input for N
 * Provides GUI manipulation of display for changing N and colors
 */
public class Checkerboard extends Application {

    // member fields for GUI size variables
    private Stage stage;
    private double sceneLength;
    private double toolBarHeight = 50;

    // for determining squares to display in checkerboard
    private int nSquares;

    // for changing color of checkerboard, hex RGB vals as strings
    private String color1 = "000000";   // Default Start color1 as Black
    private String color2 = "FFFFFF";   // Default Start color1 as White

    @Override
    public void start(Stage primaryStage) {
        initStage(primaryStage);

        this.nSquares = getInitialNSquares();

        displayCheckerboard();
    }

    /**
     * Initializes the stage settings for the application and creates a blank
     * scene for program. Not doing this causes the first checkerboard displayed
     * to not fill the entire displayed scene until a new color/size is selected
     * @param primaryStage
     */
    private void initStage(Stage primaryStage) {
        determineScreenSize();
        this.stage = primaryStage;

        /*
            this
        */
        this.stage.setScene(
            new Scene(
                new VBox(), this.sceneLength, this.sceneLength));
        this.stage.show();

        this.stage.setTitle("Checkerboard Display Demonstration");
        this.stage.setResizable(false);
    }

    /**
     * This method determines the current screen display size used where the
     * application will launch. It sets this.sceneLength to 2/3rds the smallest
     * dimension detected.
     */
    private void determineScreenSize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        this.sceneLength =
            (screenWidth > screenHeight) ? screenHeight : screenWidth;

        this.sceneLength /= 1.5;
    }

    /**
     * Displays a TextInputDialog for user to enter the initial number of
     * squares per row to display and validates input
     * @return user input as integer
     */
    private int getInitialNSquares() {
        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("How big do you want your checkerboard to be (NxN)?");
        td.setContentText("Enter a positive integer value:");
        td.showAndWait();

        // validate input, recur if invalid
        try {
            validateIntInput(td.getEditor().getText());
        }
        catch (IllegalArgumentException e) {
            alertInvalidIntInput(e.getMessage());
            return getInitialNSquares();
        }

        return Integer.parseInt(td.getEditor().getText());
    }

    /**
     * Method for validating input from user
     * @param input as String
     * @throws IllegalArgumentException for:
     *      Non-integer values, leading 0, or 0 alone
     *      Values greater than 100
     *      (Because Checkerboard creation space/time complexity is O(n^2),
     *      larger values can cause program to freeze or crash while JavaFX
     *      builds the TextField elements)
     */
    private void validateIntInput(String input) throws IllegalArgumentException {
        // regex only positive integers not starting with 0
        if (!input.matches("^[1-9][0-9]*$")) {
            throw new IllegalArgumentException("Invalid Entry");
        }
        if (Integer.parseInt(input) > 100) {
            throw new IllegalArgumentException("Value Too Large");
        }
    }

    /**
     * Helper method for alerting user to invalid input
     * @param exceptionMsg reason for bad input
     */
    private void alertInvalidIntInput(String exceptionMsg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error: " + exceptionMsg);
        alert.setContentText(
            "Error: " + exceptionMsg + "\n\n" +
            "That was not a valid entry.\n" +
            "Please enter a positive integer value under 100."
        );

        alert.showAndWait();
    }

    /**
     * Calls methods to create/update/display the checkerboard
     */
    private void displayCheckerboard() {
        Scene scene = new Scene(
            new VBox(), this.sceneLength, this.sceneLength+this.toolBarHeight
        );

        VBox box = (VBox)scene.getRoot();
        box.getChildren().add(createToolBar());
        box.getChildren().add(createCheckerBoard());

        changeScene(scene);
    }

    /**
     * Creates the ToolBar GUI for interacting with the display
     * @return ToolBar object
     */
    private ToolBar createToolBar() {
        ToolBar tb = new ToolBar();
        tb.setMinHeight(this.toolBarHeight);
        tb.setMaxHeight(this.toolBarHeight);

        Label changeSizeLbl = new Label("Enter a new size:");

        TextField changeSizeTf = new TextField();
        changeSizeTf.setMinWidth(40);
        changeSizeTf.setMaxWidth(40);

        Button changeSizeBtn = new Button("Change Size");

        changeSizeBtn.setOnAction(event -> {
            try {
                validateIntInput(changeSizeTf.getText());
                this.nSquares = Integer.parseInt(changeSizeTf.getText());
            }
            catch (IllegalArgumentException e) {
                alertInvalidIntInput(e.getMessage());
            }
            displayCheckerboard();
        });

        // add a ColorPicker for color 1 with event handling to change colors
        ColorPicker cp1 = new ColorPicker(Color.web(this.color1));
        cp1.setOnAction(event -> {
            this.color1 = cp1.getValue().toString().substring(2,8);
            displayCheckerboard();
        });

        // add color 2 ColorPicker & event handling
        ColorPicker cp2 = new ColorPicker(Color.web(this.color2));
        cp2.setOnAction(event -> {
            this.color2 = cp2.getValue().toString().substring(2,8);
            displayCheckerboard();
        });

        // build tool bar and return
        tb.getItems().addAll(
            changeSizeLbl, changeSizeTf, changeSizeBtn, cp1, cp2
        );
        return tb;
    }

    /**
     * Creates the checkerboard
     * @return VBox containing HBox rows for display
     */
    private VBox createCheckerBoard() {
        VBox board = new VBox();

        for (int i = 0; i < nSquares; i++) {
            board.getChildren().add(
                createRow(i)
            );
        }

        return board;
    }

    /**
     * Creates a row of squares
     * @param rowNum current row being created
     * @return HBox containing squares
     */
    private HBox createRow(int rowNum) {
        HBox row = new HBox();

        double squareLength = this.sceneLength / (double)this.nSquares;

        for (int colNum = 0; colNum < this.nSquares; colNum++) {

            // alternate colors for each square
            Boolean isColor1 = (rowNum+colNum) % 2 == 0;

            row.getChildren().add(
                createSquare(squareLength, isColor1)
            );
        }

        return row;
    }

    /**
     * Creates a square for the Checkerboard display
     * @param length of the square
     * @param isColor1 color alternater
     * @return created square
     */
    private TextField createSquare(double length, Boolean isColor1) {
        TextField square = new TextField();
        square.setDisable(true);
        square.setMinSize(length, length);
        square.setMaxSize(length, length);

        // set colors
        if (isColor1) {
            square.setStyle("-fx-background-color: #" + this.color1);
        }
        else {
            square.setStyle("-fx-background-color: #" + this.color2);
        }

        return square;
    }

    /**
     * Method sets a new @param scene and displays it on the stage
     */
    private void changeScene(Scene scene) {
        this.stage.setScene(scene);
        this.stage.show();
    }

    /**
     * Main Method launches GUI Application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
