package exoticmoves;

import java.time.LocalDate;
import java.time.YearMonth;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * PurchaseHandler handles purchase events
 *
 * Validates input for text fiends
 *
 * This was created in an attempt to simplify some of the functionality of the
 * purchase GUI element or at least get some of the mess away from the building
 * of the actual display.
 *
 * Messy but working.
 */
public final class PurchaseHandler {
    private static String firstName;
    private static String lastName;
    private static String ccNumber;
    private static YearMonth ccExp;
    private static String ccVerificationNum;

    // private constructor, do not instantiate
    private PurchaseHandler() {
    }

    // enum for creating input areas
    public static enum InputType {
        FIRST_NAME, LAST_NAME, CCN, CCV
    }

    /**
     * Creates a text input area with handling for loss of focus
     * and display text for invalid input
     * @param boxLabel
     * @param inputType
     * @return
     */
    public static VBox createInputArea(String boxLabel, InputType inputType) {
        // create elements
        Label label = new Label(boxLabel);
        TextField textField = new TextField();
        Text invalidWarning = new Text("Invalid Entry!");
        invalidWarning.setVisible(false);

        // add handling
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            String input = textField.getText();
            if (!input.isEmpty() && input != null) {
                addWarningHandler(input, invalidWarning, inputType);
            }
        });

        // box and return
        return new VBox(label, textField, invalidWarning);
    }

    /**
     * Method turns an invalid input warning on or off if any input box has bad
     * input
     * @param strToCheck
     * @param warning
     * @param inputType
     * @return
     */
    private static Text addWarningHandler(String strToCheck, Text warning,
                                          InputType inputType)
    {
        switch (inputType)
        {
        case FIRST_NAME:
            if (!InputValidator.onlyLetters(strToCheck, false)) {
                warning.setVisible(true);
                firstName = null;
            }
            else {
                warning.setVisible(false);
                firstName = strToCheck;
            }
            break;

        case LAST_NAME:
            if (!InputValidator.onlyLetters(strToCheck, false)) {
                warning.setVisible(true);
                lastName = null;
            }
            else {
                warning.setVisible(false);
                lastName = strToCheck;
            }
            break;

        case CCN:
            if (!InputValidator.onlyDigits(strToCheck, 16, false)) {
                warning.setVisible(true);
                ccNumber = null;
            }
            else {
                warning.setVisible(false);
                ccNumber = strToCheck;
            }
            break;

        case CCV:
            if (!InputValidator.onlyDigits(strToCheck, 3, false)) {
                warning.setVisible(true);
                ccVerificationNum = null;
            }
            else {
                warning.setVisible(false);
                ccVerificationNum = strToCheck;
            }

            break;
        }

        return warning;
    }

    /**
     * Creates the credit card expiration input area
     * @return
     */
    public static VBox createExpirationPane() {
        Label label = new Label("Expiration");

        // create combobox for month
        ComboBox<Integer> monthCb = new ComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthCb.getItems().add(i);
        }

        // create combobox for year starting with current year
        ComboBox<Integer> yearCb = new ComboBox<>();
        int currentYear = LocalDate.now().getYear();
        int yearsToShow = 25;
        for (int i = currentYear; i <= currentYear + yearsToShow; i++) {
            yearCb.getItems().add(i);
        }

        // box together horizontally
        HBox dateBox = new HBox(monthCb, yearCb);

        // warning for bad entry
        Text warningText = new Text("Invalid Entry!");
        warningText.setVisible(false);

        // event handler for displaying a warning for bad year/month
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // if both combo boxes are not null:
                if (monthCb.getValue() != null && yearCb.getValue() != null) {
                    // if bad year month entered:
                    if (!InputValidator.yearMonthNotBeforeNow(
                                yearCb.getValue(), monthCb.getValue()
                            )
                        )
                    {// display warning and set expiration as null
                        warningText.setVisible(true);
                        ccExp = null;
                    }
                    else {// until good input
                        warningText.setVisible(false);
                        ccExp = YearMonth.of(
                            yearCb.getValue(), monthCb.getValue()
                        );
                    }
                }
            }
        };

        // add above mess as event handler to comboboxes
        monthCb.setOnAction(event);
        yearCb.setOnAction(event);

        return new VBox(label, dateBox, warningText);
    }

    /**
     * Creates a receipt pane from @param purchasedCar
     * @return VBox w/ receipt information
     */
    public static VBox generateReceipt(Car purchasedCar) {
        Label label = new Label("Thank you for your purchase!");
        label.setStyle("-fx-font-size: 1.4em");

        Text purchaseInfo = new Text(
            "Purchaser: " + firstName + " " + lastName + "\n" +
            "Car information: " +
            purchasedCar.getColor() + " " +
            purchasedCar.getBrand() +
            (purchasedCar.isConvertible() ? " Convertible" : "") + "\n" +
            "Your card has been charged: $" + purchasedCar.getPrice() + ",000" +
            "\n\n" +
            "We will contact you shortly about receiving your new vehicle!" +
            "\n\n"
        );
        purchaseInfo.getStyleClass().add("dynamicDisplay");

        VBox container = new VBox(label, purchaseInfo);
        return container;
    }

    /**
     * Makes sure all fields are populated/not null
     * @return
     */
    public static Boolean validInformation() {
        return (
            firstName != null &&
            lastName != null &&
            ccNumber != null &&
            ccExp != null &&
            ccVerificationNum != null
        );
    }

    /**
     * Debug method for checking which fields are null preventing submission
     * @return
     */
    public static String debugString() {
        return (
            "Fname: " + (firstName == null) + "\n" +
            "Lname: " + (lastName == null) + "\n" +
            "CCN: " + (ccNumber == null) + "\n" +
            "Exp: " + (ccExp == null) + "\n" +
            "CCV: " + (ccVerificationNum == null) + "\n"
        );
    }
}
