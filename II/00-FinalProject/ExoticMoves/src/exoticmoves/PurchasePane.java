package exoticmoves;

import exoticmoves.ExoticMoves.CloseDisplayHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.BlurType;

/**
 * PurchasePane displays details about a car and provides the functionality to
 * purchase a car
 */
public class PurchasePane extends DisplayArea {

    private Car selectedCar;
    private Inventory inventory;

    private Pane userEntryForm;
    private Pane carDetailsPane;

    CloseDisplayHandler closeDisplayHandler;

    private GridPane root;

    /**
     * Constructor
     * @param width
     * @param height
     * @param selectedCar
     * @param inventory
     * @param closeDisplayHandler for closing the display
     */
    public PurchasePane(
        Double width, Double height, Car selectedCar, Inventory inventory,
        CloseDisplayHandler closeDisplayHandler
    )
    {
        super(width, height);
        this.selectedCar = selectedCar;
        this.inventory = inventory;
        this.closeDisplayHandler = closeDisplayHandler;

        root = new GridPane();
        super.setContainer(root);
        super.setCssClass("dynamicDisplay");

        createCarDetailsPane();
        root.getChildren().add(carDetailsPane);
    }

    /**
     * Creates the pane to hold the details about the chosen car
     */
    private void createCarDetailsPane() {
        Text brand = new Text(
            selectedCar.getColor() + " " +
            selectedCar.getBrand() +
            (selectedCar.isConvertible() ? " Convertible": "")
        );

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(2);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        brand.setEffect(dropShadow);
        brand.getStyleClass().add("dynamicDisplay");
        brand.setStyle("-fx-font-size: 34;");


        ImageView img = new ImageView(selectedCar.getImage());
        img.setFitHeight(200);
        img.setFitWidth(400);

        Text details = new Text(
            selectedCar.getCylinders() +
            " cylinders with a 0-60 performance of " +
            selectedCar.getPerformance() + " seconds."
        );
        details.getStyleClass().add("dynamicDisplay");

        Text price = new Text(
            "Available now for $" + selectedCar.getPrice() + ",000!"
        );
        price.getStyleClass().add("dynamicDisplay");
        price.setStyle("-fx-font-size: 1.4em;");
        price.setEffect(dropShadow);

        VBox carDisplayBox = new VBox(brand, img, details, price);

        Button purchaseButton = new Button("Purchase Now!");

        Text goBack = new Text("Go back");
        goBack.getStyleClass().add("dynamicDisplay");
        goBack.setOnMouseClicked(closeDisplayHandler);

        // add handler for purchasing
        purchaseButton.setOnAction(e -> {
            purchaseButton.setVisible(false);
            goBack.setVisible(false);
            displayUserInfoForm();
        });

        // box up elements
        this.carDetailsPane = new VBox(carDisplayBox, purchaseButton, goBack);
    }

    /**
     * Displays the user entry form
     */
    private void displayUserInfoForm() {
        // remove if already present
        carDetailsPane.getChildren().remove(userEntryForm);
        // create
        userEntryForm = createUserInfoForm();
        // display
        carDetailsPane.getChildren().add(userEntryForm);
    }

    /**
     * Creates the user entry form
     * @return
     */
    private Pane createUserInfoForm() {
        // create areas for entering input
        VBox firstNameBox = PurchaseHandler.createInputArea(
            "First Name", PurchaseHandler.InputType.FIRST_NAME
        );

        VBox lastNameBox = PurchaseHandler.createInputArea(
            "Last Name", PurchaseHandler.InputType.LAST_NAME
        );

        VBox creditCardNumberBox = PurchaseHandler.createInputArea(
            "Credit Card Number", PurchaseHandler.InputType.CCN
        );

        VBox creditCardExpBox = PurchaseHandler.createExpirationPane();

        VBox creditCardVerificationNumber = PurchaseHandler.createInputArea(
            "CCV", PurchaseHandler.InputType.CCV
        );

        // create buttons for form interaction
        Button submitButton = new Button("Submit");
        Button resetButton = new Button("Reset");
        HBox buttonBox = new HBox(submitButton, resetButton);

        Text goBack = new Text("Cancel Purchase");
        goBack.getStyleClass().add("dynamicDisplay");

        // add event handlers
        submitButton.setOnAction(e -> {
            if (PurchaseHandler.validInformation()) {
                purchaseCar();
            }
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error: Invalid Input");
                alert.setContentText(
                    "Please fill out form entirely and make sure all input" +
                    " is valid.\n\n" +
                    "Names can contain only letters.\n" +
                    "Credit Card must contain 16 numbers.\n" +
                    "Credit Card cannot be expired.\n" +
                    "Verification numbers consist of only 3 numbers.\n"
                );
                alert.showAndWait();
            }
        });

        // refresh display
        resetButton.setOnAction(e -> {
            displayUserInfoForm();
        });

        // close display pane and go back to the list
        goBack.setOnMouseClicked(closeDisplayHandler);

        // box up and return
        return new VBox(
            firstNameBox, lastNameBox, creditCardNumberBox,
            creditCardExpBox, creditCardVerificationNumber,
            buttonBox, goBack
        );
    }

    /**
     * Method to purchase chose car
     * Display receipt and update the inventory
     */
    private void purchaseCar() {
        inventory.removeCar(selectedCar);
        Pane receipt = PurchaseHandler.generateReceipt(selectedCar);
        displayReceipt(receipt);
    }

    /**
     * Displays @param receipt
     */
    private void displayReceipt(Pane receipt) {
        root.getChildren().clear();

        Text close = new Text("Close this receipt");
        close.getStyleClass().add("dynamicDisplay");
        close.setOnMouseClicked(closeDisplayHandler);

        root.add(receipt, 0,0);
        root.add(close, 0,1);
    }
}
