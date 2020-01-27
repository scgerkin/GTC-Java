package bmiserver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

/**
 * Client application for getting a person's weight in pounds and height in inches
 * Communicates with a server application to receive and display BMI information
 */
public class Exercise31_01Client extends Application {

    // GUI elements
    @FXML
    private TextField tfWeight;
    @FXML
    private TextField tfHeight;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextArea taClientLog;

    // for setting up connection with a server
    private ObjectInputStream fromServer;
    private DataOutputStream toServer;
    private String host = "localhost";

    /**
     * Initializes the GUI and connects to the server
     * @param primaryStage GUI stage created during launch
     */
    @Override
    public void start(Stage primaryStage) {
        initializeGUI(primaryStage);
        connectToServer();
    }

    /**
     * Connects to a server and opens socket for sending and receiving data
     */
    private void connectToServer() {
        try {
            // open connection and streams
            Socket socket = new Socket(host, 8000);
            fromServer = new ObjectInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            // inform user of connection
            log(new Date() + ": Server connection established.\n");

        }
        catch (IOException ex) {
            log("Error connecting to server", ex.toString());
        }
    }

    /**
     * Grabs the GUI elements from the FXML. This is hacked together and most
     * definitely not a best practice. I'm still learning how to work with
     * Scene Builder and the MVC model with JavaFX. However, because this program
     * is to get practice implementing a server and client connection, this will
     * do for now
     * //fixme
     * @param primaryStage the Stage element created by launching application
     */
    private void initializeGUI(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("client_layout.fxml"));

        try {
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        // Grab the GUI elements
        // require these to be not null
        // crash application if there is a problem here to debug further
        tfWeight = Objects.requireNonNull((TextField)loader.getNamespace().get("tfWeight"));
        tfHeight = Objects.requireNonNull((TextField)loader.getNamespace().get("tfHeight"));
        btnSubmit = Objects.requireNonNull((Button)loader.getNamespace().get("btnSubmit"));
        taClientLog = Objects.requireNonNull((TextArea)loader.getNamespace().get("taLog"));

        // add event handling to the button
        btnSubmit.setOnAction(new SubmitButtonHandler());
    }

    /**
     * Handling class for the Submit button
     * Gets values entered in the TextField elements and validates input for
     * non-negative Double values.
     */
    private class SubmitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                // get the values and validate the input
                double weight = Double.parseDouble(tfWeight.getText());
                double height = Double.parseDouble(tfHeight.getText());
                if (weight <= 0 || height <= 0) {
                    throw new NumberFormatException();
                }
                // send the values to the server
                toServer.writeDouble(weight);
                toServer.writeDouble(height);

                // get the result back
                String bmi = (String)fromServer.readObject();

                // display the result
                log(bmi);

            }
            catch (NumberFormatException ex) {
                log("Invalid input. Please enter only numbers.");
            }
            catch (ClassNotFoundException ex) {
                log("ERROR server sent garbage.", ex.toString());
            }
            catch (IOException ex) {
                log("ERROR connecting to server.", ex.toString());
            }
        }
    }

    /**
     * Wrapper for logging strings to the GUI TextArea
     * Runs in a separate thread from socket processing
     * @param str vararg strings to write to the TextArea
     */
    private void log(String ... str) {
        Platform.runLater(() -> {
            for (String s: str) {
                taClientLog.appendText(s + "\n");
            }
        });
    }
}
