
package travelexpenses;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Stephen Gerkin
 * @date 2019-06-12
 * Programming Lab 8 - 1. Travel Expenes
 * GUI Program to collect travel expenses and calculate reimbursement
 */
public class ProgramDriver extends Application {

    // member Stage and Scene items
    private Stage mainStage;
    private Scene dataCollect;

    // label for displaying rental car or miles driven
    private Label vehicleMethodLabel;

    // ExpenseCalculator obj to work with program logic
    private ExpenseCalculator expCalc;

    // Textfields for data collection
    private TextField tripDaysTF;
    private TextField airfareTF;
    private TextField conferenceTF;
    private TextField lodgingTF;
    private TextField vehicleTF;
    private TextField parkingTF;
    private TextField taxiTF;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initStage(primaryStage);
        createDataCollectScene();
        setScene(dataCollect);

    }

    /**
     * Creates the first scene for the program to collect the data
     * This is a mess and in need of major refactoring...
     */
    private void createDataCollectScene() {
        this.expCalc = new ExpenseCalculator();

        Label tripDaysLabel = new Label("Total Trip Days:");
        this.tripDaysTF = new TextField("0");

        Label airfareLabel = new Label("Total Airfare Expense:");
        this.airfareTF = new TextField("0");

        Label conferenceLabel = new Label("Conference / Registration Fees:");
        this.conferenceTF = new TextField("0");

        Label lodgingLabel = new Label("Lodging Expense (per night):");
        this.lodgingTF = new TextField("0");

        VBox top = new VBox(
            10, tripDaysLabel, tripDaysTF, airfareLabel, airfareTF,
            conferenceLabel, conferenceTF, lodgingLabel, lodgingTF
        );
        top.setMaxWidth(200);

        ToggleGroup vehicle = new ToggleGroup();
        Label vehicleLabel = new Label("On-Site Transportation Type:");
        RadioButton carRental = new RadioButton("Rental");
        carRental.setToggleGroup(vehicle);
        carRental.setSelected(true);
        RadioButton privateVehicle = new RadioButton("Private Vehicle");
        privateVehicle.setToggleGroup(vehicle);

        HBox radioButtons = new HBox(10, carRental, privateVehicle);

        this.vehicleMethodLabel = new Label(
            "Total Rental Fees (include mileage):"
        );
        this.vehicleTF = new TextField("0");

        vehicle.selectedToggleProperty().addListener(e -> {
                if (carRental.isSelected()) {
                    vehicleMethodLabel.setText(
                        "Total Rental Fees (include mileage):"
                    );
                }
                else {
                    vehicleMethodLabel.setText("Total Miles Driven:");
                }
            });

        Label parkingLabel = new Label("Total Parking Fees:");
        this.parkingTF = new TextField("0");

        Label taxiLabel = new Label("Total Taxi Fees:");
        this.taxiTF = new TextField("0");

        VBox middle = new VBox(
            10, vehicleLabel, radioButtons,this.vehicleMethodLabel, vehicleTF,
            parkingLabel, parkingTF, taxiLabel, taxiTF
        );
        middle.setMaxWidth(200);

        Button calc = new Button("Calculate");
        calc.setOnAction(new CalcButton());

        VBox getData = new VBox(10, top, middle, calc);
        getData.setAlignment(Pos.TOP_LEFT);

        this.dataCollect = new Scene(getData);
    }

    /**
     * Handles collecting data on button event and calls new scene to display
     * the results
     */
    private class CalcButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            expCalc.setTripDays(Integer.parseInt(tripDaysTF.getText()));
            expCalc.setAirfare(Double.parseDouble(airfareTF.getText()));
            expCalc.setConferenceFees(Double.parseDouble(conferenceTF.getText()));
            expCalc.setLodgingCharges(Double.parseDouble(lodgingTF.getText()));
            expCalc.setParkingFees(Double.parseDouble(parkingTF.getText()));
            expCalc.setTaxiCharges(Double.parseDouble(taxiTF.getText()));

            Double vehicle = Double.parseDouble(vehicleTF.getText());

            if (expCalc.carRented()) {
                expCalc.setCarRentalFee(vehicle);
            }
            else {
                expCalc.setMilesDriven(vehicle);
            }

            displayResults();
        }
    }

    /**
     * Method to display the results after data has been collected
     */
    private void displayResults() {
        Double expensesVal = expCalc.getTotalExpenses();
        double reimbursementActual = expCalc.getActualReimbursement();
        double reimbursementMax = expCalc.getMaxReimbursement();
        Double balanceVal = expensesVal - reimbursementMax;

        Label expensesLabel = new Label(
            String.format(
                "Total Expenses: $%.2f", expensesVal
        ));

        Label reimbursementMaxLabel = new Label(
            String.format(
                "Maximum Reimbursement: $%.2f", reimbursementMax
        ));

        Label reimbursementActualLabel = new Label(
            String.format(
                "Total Reimbursement: $%.2f", reimbursementActual
        ));
        Label balanceLabel = new Label();

        if (balanceVal > 0) {   // over budget
            balanceLabel.setText(
                String.format(
                    "Total Over Budget: $%.2f", balanceVal
            ));
            balanceLabel.setTextFill(Color.web("#FF0000"));
            balanceLabel.setStyle("-fx-font-weight: 700;");
        }
        else {  // under budget
            balanceLabel.setText(
                String.format(
                    "Total Money Saved: $%.2f", (-1*balanceVal)
            ));
            balanceLabel.setTextFill(Color.web("#00FF00"));
            balanceLabel.setStyle("-fx-font-weight: 700;");
        }

        // reset button to go back to first scene
        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            createDataCollectScene();
            setScene(dataCollect);
        });

        // aggregate items and set as scene
        VBox display = new VBox(
            10, expensesLabel, reimbursementMaxLabel, reimbursementActualLabel,
            balanceLabel, reset
        );

        setScene(new Scene(display));
    }

    /**
     * Sets this.mainStage to the primaryStage created by JavaFX and
     * initializes the stage values
     * @param primaryStage
     */
    private void initStage(Stage primaryStage) {
        this.mainStage = primaryStage;
        this.mainStage.setHeight(600);
        this.mainStage.setWidth(215);
        this.mainStage.setResizable(true);
    }

    /**
     * Sets the current scene and triggers the stage to refresh
     * @param newScene
     */
    private void setScene(Scene newScene) {
        this.mainStage.setScene(newScene);
        this.mainStage.show();
    }
}
