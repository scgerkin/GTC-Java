package exercise32_06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Connects to the Access Database file in the dependencies folder and allows
 * the user to view all records for each table in the DB
 */
public class Exercise32_06 extends Application {

    // javaFX widgets
    private ComboBox<String> cbTableNames = new ComboBox<>();
    private Button btnShowContents = new Button("Show Contents");
    private Label lblStatus = new Label();

    // for displaying the results from the database
    private TableView<ObservableList> tvResults = new TableView<>();

    // sql objects for database interaction
    private Connection connection;
    private Statement statement;

    // for holding the table names after connecting to DB
    private List<String> tableNames = new ArrayList<>();

    /**
     * Starts the app
     * @param primaryStage the main Stage created at launch
     */
    @Override
    public void start(Stage primaryStage) {
        initDBConnection();
        buildAndDisplayGUI(primaryStage);
    }

    /**
     * Builds and displays the GUI
     * @param primaryStage the main Stage created at launch
     */
    private void buildAndDisplayGUI(Stage primaryStage) {
        int appWidth = 920; // about the size of the largest table in current DB
        int appHeight = 500;

        // get the list of tables from the database and put them in the ComboBox
        cbTableNames.getItems().addAll(FXCollections.observableArrayList(tableNames));

        // for selecting Table
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(
            new Label("Table Name"),
            cbTableNames,
            btnShowContents
        );
        hBox.setAlignment(Pos.CENTER);

        // build root
        BorderPane root = new BorderPane();
        tvResults.setEditable(false);
        tvResults.setPrefSize(appWidth-5, appHeight-50);
        root.setCenter(new ScrollPane(tvResults));
        root.setTop(hBox);
        root.setBottom(lblStatus);

        // create a scene and place it in the stage
        Scene scene = new Scene(root, appWidth, appHeight);
        primaryStage.setTitle("Exercise32_06");
        primaryStage.setScene(scene);
        primaryStage.show();

        // make sure connection to database is closed on exit and program ends
        primaryStage.setOnCloseRequest(e -> {
            try {
                connection.close();
            }
            catch (SQLException ex) {
                // shouldn't happen but log it just in case
                System.err.println("Attempt to close connection to Database failed.");
            }
            Platform.exit();
            System.exit(0);
        });

        // make button do things
        btnShowContents.setOnAction(e -> showContents());
    }

    /**
     * Initializes all connections and objects required to interface with the DB
     */
    private void initDBConnection() {
        loadDriver();
        connectToDB();
        getTableNames();
        initializeStatement();
    }

    /**
     * Loads the Drivers for Access files
     */
    private void loadDriver() {
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        try {
            Class.forName(driver);
            System.out.println("Driver loaded");
        }
        catch (ClassNotFoundException ex) {
            lblStatus.setText("Error loading Driver " + driver);
            System.err.println("Error loading Driver " + driver);
            ex.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the database file in the dependencies
     */
    private void connectToDB() {
        File file = new File("dependencies/exampleMDB.accdb");
        String databasePath = "jdbc:ucanaccess://" + file.getAbsolutePath();
        try {
            connection = DriverManager.getConnection(databasePath);
            System.out.println("Database connected");
        }
        catch (SQLException ex) {
            lblStatus.setText("Error connecting to database: " + databasePath);
            System.err.println("Error connecting to database: " + databasePath);
            ex.printStackTrace();
        }
    }

    /**
     * Gets all the table names from the Database and stores them in the tableNames
     * List
     */
    private void getTableNames() {
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();

            ResultSet rsTables = dbMetaData.getTables(
                null,
                null,
                null,
                new String[] {"TABLE"}
            );

            while (rsTables.next()) {
                tableNames.add(rsTables.getString("TABLE_NAME"));
            }
        }
        catch (SQLException ex) {
            lblStatus.setText("Error retrieving Database MetaData: Table Names");
            System.err.println("Error retrieving Database MetaData: Table Names");
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the Statement object used to query the database
     */
    private void initializeStatement() {
        try {
            statement = connection.createStatement();
        }
        catch (SQLException ex) {
            lblStatus.setText("Error creating Statement Object");
            System.err.println("Error creating Statement Object");
            ex.printStackTrace();
        }
    }

    /**
     * Selects all records from selected table in the Database and builds a
     * TableView of the records
     */
    private void showContents() {
        String tableName = cbTableNames.getSelectionModel().getSelectedItem().trim();

        try {
            String queryString = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(queryString);

            buildTable(resultSet);
        }
        catch (SQLException ex) {
            lblStatus.setText("Error building data.");
            System.err.println("Error building data.");
            ex.printStackTrace();
        }
    }

    /**
     * Adopted and modified from:
     * https://blog.ngopal.com.np/2011/10/19/dyanmic-tableview-data-from-database/
     *
     * Clears the current TableView object and rebuilds it with the data from the
     * ResultSet data from a table.
     *
     * @param resultSet The ResultSet data retrieved from a Table
     * @throws SQLException if there is any problem retrieving the data
     */
    @SuppressWarnings("unchecked")
    private void buildTable(ResultSet resultSet) throws SQLException {
        // clear the data if there is any
        tvResults.getColumns().clear();
        tvResults.getItems().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        int columnCount = resultSet.getMetaData().getColumnCount();

        // get the Column names
        for (int i = 1; i <= columnCount; i++) {
            TableColumn<ObservableList, String> col = new TableColumn<>(resultSet.getMetaData().getColumnName(i));
            final int j = i-1;
            col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j).toString()));
            tvResults.getColumns().addAll(col);
        }

        // get the records and put them in the data List
        while (resultSet.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= columnCount; i++) {
                if (resultSet.getString(i) != null) {
                    row.add(resultSet.getString(i));
                }
                else {
                    row.add("NULL");
                }
            }
            data.add(row);
        }

        // add everything to the TableView
        tvResults.setItems(data);
        lblStatus.setText("Displaying table information for table " + resultSet.getMetaData().getTableName(1));
    }

    /**
     * Launches JavaFX
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
