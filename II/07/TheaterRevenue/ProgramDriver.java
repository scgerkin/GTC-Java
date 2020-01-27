package theaterrevenue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Stephen Gerkin
 * @date 2019-06-12
 * Programming Lab 8 - 2. Theater Revenue
 * Program to get ticket sales for a theater, calc, and display revenues
 */
public class ProgramDriver extends Application {

    private Stage mainStage;
    private Scene dataCollect;
    private RevenueCalculator revCalc;

    private TextField adultTicketsTF;
    private TextField adultPriceTF;
    private TextField childTicketsTF;
    private TextField childPriceTF;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initStage(primaryStage);
        displayDataCollect();

    }

    /**
     * Gets data from user for ticket information
     */
    private void displayDataCollect() {
        Label adultTicketsLBL = new Label("Adult Tickets Sold:");
        this.adultTicketsTF = new TextField("0");

        Label adultPriceLBL = new Label("Adult Ticket Price:");
        this.adultPriceTF = new TextField("0");

        Label childTicketsLBL = new Label("Child Tickets Sold:");
        this.childTicketsTF = new TextField("0");

        Label childPriceLBL = new Label("Child Ticket Price:");
        this.childPriceTF = new TextField("0");

        Button calcButton = new Button("Calculate");
        calcButton.setOnAction(new CalcButton());

        VBox box = new VBox(
            10, adultTicketsLBL, adultTicketsTF, adultPriceLBL, adultPriceTF,
            childTicketsLBL,  childTicketsTF, childPriceLBL, childPriceTF,
            calcButton
        );

        this.dataCollect = new Scene(box);
        setScene(dataCollect);
    }

    /**
     * Handles calculate button press to calculate totals and change scene
     * to display totals
     */
    private class CalcButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int adultTickets = Integer.parseInt(adultTicketsTF.getText());
            double adultPrice = Double.parseDouble(adultPriceTF.getText());
            int childTickets = Integer.parseInt(childTicketsTF.getText());
            double childPrice = Double.parseDouble(childPriceTF.getText());

            revCalc = new RevenueCalculator(
                adultTickets, adultPrice, childTickets, childPrice
            );

            displayResults();
        }
    }

    /**
     * Displays calculation results and provides a button to reset to data
     * collection scene
     */
    private void displayResults() {
        Label grossRevenueAdult = new Label(
            String.format(
                "Gross Revenue for Adult Tickets: $%.2f",
                this.revCalc.getGrossAdult()
        ));

        Label netRevenueAdult = new Label(
            String.format(
                "Net Revenue for Adult Tickets: $%.2f",
                this.revCalc.getNetAdult()
        ));

        Label grossRevenueChild = new Label(
            String.format(
                "Gross Revenue for Child Tickets: $%.2f",
                this.revCalc.getGrossChild()
        ));

        Label netRevenueChild = new Label(
            String.format(
                "Net Revenue for Child Tickets: $%.2f",
                this.revCalc.getNetChild()
        ));

        Label grossRevenueTotal = new Label(
            String.format(
                "Gross Total Revenue: $%.2f",
                this.revCalc.getGrossTotal()
        ));

        Label netRevenueTotal = new Label(
            String.format(
                "Net Total Revenue: $%.2f",
                this.revCalc.getNetTotal()
        ));

        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            displayDataCollect();
        });

        VBox box = new VBox(
            10, grossRevenueAdult, netRevenueAdult, grossRevenueChild,
            netRevenueChild, grossRevenueTotal, netRevenueTotal, reset
        );

        Scene results = new Scene(box);

        setScene(results);
    }


    /**
     * Sets this.mainStage to the primaryStage created by JavaFX and
     * initializes the stage values
     * @param primaryStage
     */
    private void initStage(Stage primaryStage) {
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Theater Revenue");
        this.mainStage.setHeight(320);
        this.mainStage.setWidth(250);
        this.mainStage.setResizable(true);
    }

    /**
     * Sets the active scene to the newScene and updates the stage to display it
     * @param newScene
     *
     */
    private void setScene(Scene newScene) {
        this.mainStage.setScene(newScene);
        this.mainStage.show();
    }

}
