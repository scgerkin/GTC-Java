package bmiserver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Date;

/**
 * Server application for calculating the BMI of a person based on client information
 * containing Weight in pounds and Height in inches
 */
public class Exercise31_01Server extends Application {

    private int port = 8000;

    // for displaying information about server actions
    private TextArea taServerLog = new TextArea();

    // for identifying individual client connections
    private int clientNum= 0;

    /**
     * Initializes the server with a TextArea for displaying log information
     * and creates a ServerSocket for allowing connections
     * @param primaryStage GUI stage created during launch
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new ScrollPane(taServerLog), 450, 200);
        primaryStage.setTitle("BMI Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        // end all threads and tasks on close
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(1);
        });

        // open server on port using a fresh thread
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                log(new Date() + ": Server started at socket " + port + "\n");

                // allow multiple client connections
                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(new HandleClient(socket, ++clientNum)).start();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * Class for handling multiple client interactions on a separate thread
     */
    private class HandleClient implements Runnable {
        // for establishing connection with client
        private Socket socket;
        DataInputStream fromClient;
        ObjectOutputStream toClient;

        // for storing client identifiers
        private int clientNum;
        private String clientName;
        private String clientAddress;

        /**
         * Constructor for handling a client connection and interaction
         * @param socket connection with a client
         * @param clientConnectionID unique ID created during connection
         */
        public HandleClient(Socket socket, int clientConnectionID) {
            this.socket = socket;
            this.clientNum = clientConnectionID;
            this.clientName = socket.getInetAddress().getHostName();
            this.clientAddress = socket.getInetAddress().getHostAddress();

            try {
                this.fromClient = new DataInputStream(socket.getInputStream());
                this.toClient = new ObjectOutputStream(socket.getOutputStream());
                log(
                    new Date() + ": Connected to a client.",
                    "Host name: " + clientName,
                    "Host address: " + clientAddress,
                    ""
                );
            }
            catch (IOException ex) {
                log(
                    "CONNECTION ERROR",
                    "Host name: " + clientName,
                    "Host address: " + clientAddress,
                    ex.toString(),
                    ""
                );
            }
        }

        /**
         * Communicates with a client connection for receiving weight & height
         * and returning BMI information
         */
        @Override
        public void run() {
            try {
                while (true) {
                    // get vals from client
                    double weight = fromClient.readDouble();
                    double height = fromClient.readDouble();
                    // calc bmi
                    String bmiStr = constructBMIInfo(weight, height);

                    // log info
                    log(
                        "Client " + clientNum + " (" + clientName + ") sends:",
                        "Weight: " + weight,
                        "Height: " + height,
                        "Returning value...",
                        bmiStr,
                        ""
                    );
                    // return bmi to client
                    toClient.writeObject(bmiStr);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Constructs a String object containing BMI value and interpretation from
     * the parameters
     * @param weight in pounds
     * @param height in inches
     * @return String containing BMI value and interpretation
     */
    private static String constructBMIInfo(double weight, double height) {
        final double KILOGRAMS_PER_POUND = 0.45359237;
        final double METERS_PER_INCH = 0.0254;

        // convert to metric and calculate
        double bmi = (weight*KILOGRAMS_PER_POUND)/(Math.pow(height*METERS_PER_INCH, 2));

        // interpret results
        String interpretation;
        if (bmi < 18.5) {
            interpretation = "Underweight";
        }
        else if (bmi < 25) {
            interpretation = "Normal";
        }
        else if (bmi < 30) {
            interpretation = "Overweight";
        }
        else {
            interpretation = "Obese";
        }

        // construct a string with the information
        return String.format("BMI is: %.2f. %s", bmi, interpretation);
    }

    /**
     * Wrapper for logging strings to the GUI TextArea
     * Runs in a separate thread from socket processing
     * @param str vararg strings to write to the TextArea
     */
    private void log(String ... str) {
        Platform.runLater(() -> {
            for (String s: str) {
                taServerLog.appendText(s + "\n");
            }
        });
    }
}
