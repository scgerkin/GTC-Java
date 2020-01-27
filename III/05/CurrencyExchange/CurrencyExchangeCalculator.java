package currencyexchangecalculator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.NumberFormat;

/**
 * Currency Exchange Calculator allows a user to enter a currency amount and
 * convert it to another currency. It will also allow the user to view how that
 * currency would be displayed in various locales by selected a desired locale.
 * Exchange rate information is updated each time the base currency changes by
 * interfacing with an API provided here:
 * https://exchangeratesapi.io/
 * This project has been modified from the prompt due to a desire to get some
 * experience implementing JSON parsing in Java and interfacing with a web API.
 */
public class CurrencyExchangeCalculator extends Application {

    // filepath for JSON file
    private static final String fileName = "rates.json";

    // for holding exchange rate data
    private ExchangeRateData exchangeRateData;

    // for getting input from user
    private TextField tfBaseCurrency = new TextField();

    // objects used for selecting currencies and locale
    private ComboBox<String> cbBaseCurrency = new ComboBox<>();
    private ComboBox<String> cbTargetCurrency = new ComboBox<>();
    private ComboBox<String> cbLocales = new ComboBox<>();
    private Locale[] availableLocales = Locale.getAvailableLocales();
    private Map<String, String> codeMap = new HashMap<>();

    // for storing the current exchange rate information
    private BigDecimal exchangeRate;

    // for displaying conversions
    private Label lblExchangeRate = new Label();
    private Label lblCurrencyExchange = new Label();

    private Button btnConvert = new Button("Convert");

    @Override
    public void start(Stage primaryStage) {
        updateExchangeRates(null);
        fillComboBoxes();
        buildAndDisplayGUI(primaryStage);
    }

    /**
     * Gets exchange rate data using the selected currency as the base currency
     * @param currencyCode the currency to use as the base currency (ie "USD")
     */
    private void updateExchangeRates(String currencyCode) {
        final String url = "https://api.exchangeratesapi.io/latest?base=" +
                               ((currencyCode == null) ? "USD" : currencyCode);
        getDataFromAPI(url);
        convertJSONFileToObject();
    }

    /**
     * Downloads a JSON file with data for exchange rates.
     * @param url the URL of the API to download information from
     */
    private void getDataFromAPI(String url) {
        try (ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream())) {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
        catch (IOException ex) {
            System.out.println("Problem getting updated data from API.");
            System.out.println("Using old data for exchange rates.");
            ex.printStackTrace();
        }
    }

    /**
     * Converts the downloaded JSON file to an ExchangeRateData object using
     * the Gson library.
     */
    private void convertJSONFileToObject() {
        File file = new File(fileName);
        Gson gson = new Gson();
        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            exchangeRateData = gson.fromJson(jsonReader, ExchangeRateData.class);
        }
        catch (IOException ex) {
            // if this happens, there's a big problem and the program should stop
            System.out.println("Problem converting file to object.");
            ex.printStackTrace();
            alertAndExit();
        }
    }

    /**
     * Handles any issue related to converting the JSON file downloaded into an
     * ExchangeRateData object. Currently warns the user of an error and shuts
     * down.
     */
    private void alertAndExit() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(
            "There was an error loading the exchange rate information.\n" +
                "Program shutting down."
        );
        alert.showAndWait();
        Platform.exit();
        System.exit(1);
    }

    /**
     * Fills the combo boxes with valid selections.
     * The currency boxes are filled with display names rather than country codes
     * i.e. "US Dollar" instead of "USD" so the user can better understand what
     * currency is to be converted. To get the right code back for using with
     * Currency objects for display, a HashMap is created using the display name
     * as the key and the code as the value so that these can be retrieved as
     * needed in a cost effective manner.
     */
    private void fillComboBoxes() {
        // fill currency options and the map to convert from display name to code
        for (Map.Entry<String, BigDecimal> entry : exchangeRateData.getRates().entrySet()) {
            String currencyName = Currency.getInstance(entry.getKey()).getDisplayName();
            codeMap.put(currencyName, entry.getKey());
            cbBaseCurrency.getItems().add(currencyName);
            cbTargetCurrency.getItems().add(currencyName);
        }

        // set the currency boxes to select the base currency from the API
        cbBaseCurrency.getSelectionModel().select(
            Currency.getInstance(exchangeRateData.getBase()).getDisplayName()
        );
        cbTargetCurrency.getSelectionModel().select(
            Currency.getInstance(exchangeRateData.getBase()).getDisplayName()
        );

        // get all the locales into a combobox and select the current locale
        for (Locale locale : availableLocales) {
            cbLocales.getItems().add(locale.getDisplayName());
            if (locale.equals(Locale.getDefault())) {
                cbLocales.getSelectionModel().selectLast();
            }
        }
    }

    /**
     * Builds and displays the GUI
     * @param primaryStage the stage created at launch
     */
    private void buildAndDisplayGUI(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(new Label("Enter base amount:"), 0, 0);
        gridPane.add(tfBaseCurrency, 0, 1);
        gridPane.add(new Label("Base Currency"), 1, 0);
        gridPane.add(cbBaseCurrency, 1, 1);
        gridPane.add(new Label("Target Currency"), 0, 2);
        gridPane.add(cbTargetCurrency, 0, 3);
        gridPane.add(new Label("Locale"), 1, 2);
        gridPane.add(cbLocales, 1, 3);

        VBox root = new VBox(5);
        root.getChildren().addAll(
            gridPane, btnConvert, lblExchangeRate, lblCurrencyExchange
        );

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Currency Exchange Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // make sure everything shuts down on exit
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        // handle bad input when trying to convert
        btnConvert.setOnAction(e -> {
            try {
                calcAndDisplayExchange();
            }
            catch (NumberFormatException ex) {
                handleBandInput();
            }
        });
    }

    /**
     * Handles improper user input for currency amount to be converted.
     */
    private void handleBandInput() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a valid number");
        alert.showAndWait();
    }

    /**
     * Calculates the exchange and updates the labels that display the exchange
     * rate information.
     * @throws NumberFormatException if the amount entered for conversion is not
     * a valid number entry.
     */
    private void calcAndDisplayExchange() throws NumberFormatException {
        determineExchangeRate();
        // get amounts
        BigDecimal baseAmt = new BigDecimal(tfBaseCurrency.getText());
        BigDecimal targetAmt = baseAmt.multiply(exchangeRate);

        // display exchange rate
        lblExchangeRate.setText("Exchange rate is " + exchangeRate.toString());

        // get locale for formatting numbers
        Locale locale = availableLocales[cbLocales.getSelectionModel().getSelectedIndex()];
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        // get the currency code for the base and target currencies
        String baseCode = codeMap.get(cbBaseCurrency.getSelectionModel().getSelectedItem());
        String targetCode = codeMap.get(cbTargetCurrency.getSelectionModel().getSelectedItem());

        // build the output string and display
        numberFormat.setCurrency(Currency.getInstance(baseCode));
        StringBuilder msg = new StringBuilder(numberFormat.format(baseAmt) + " is ");
        numberFormat.setCurrency(Currency.getInstance(targetCode));
        msg.append(numberFormat.format(targetAmt));

        lblCurrencyExchange.setText(msg.toString());
    }

    /**
     * Determines if the exchange rates have changed and what they are updating
     * the exchangeRate if necessary.
     */
    private void determineExchangeRate() {
        String base = codeMap.get(cbBaseCurrency.getSelectionModel().getSelectedItem());
        if (!base.equals(exchangeRateData.getBase())) {
            updateExchangeRates(base);
        }
        String target = codeMap.get(cbTargetCurrency.getSelectionModel().getSelectedItem());
        exchangeRate = exchangeRateData.getRate(target);
    }
    
    /** For IDEs that need it */
    public static void main(String[] args) {
        launch(args);
    }
}
