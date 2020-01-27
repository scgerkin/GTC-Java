package colorradiobuttons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *
 * @author Stephen Gerkin
 * @date 2019-06-14
 * Programming Lab 10 - 7 Color Changing Radio Buttons
 * Quick program to demonstrate radio buttons by toggling the background color
 * of an application
 */
public class ColorRadioButtons extends Application {

    @Override
    public void start(Stage stage) {
        stage.setHeight(300);
        stage.setWidth(300);
        stage.setTitle("Radio Buttons");

        // toggle buttons
        ToggleGroup tg = new ToggleGroup();
        RadioButton yellow = new RadioButton("Yellow");
        RadioButton white = new RadioButton("White");

        yellow.setToggleGroup(tg);
        white.setToggleGroup(tg);
        white.setSelected(true);

        // put buttons in box
        HBox hbox = new HBox(10, white, yellow);
        hbox.setStyle("-fx-background-color: #FFFFFF");
        hbox.setAlignment(Pos.CENTER);

        // add listeners to change background color
        tg.selectedToggleProperty().addListener(e -> {
            if (yellow.isSelected()) {
                hbox.setStyle("-fx-background-color: #FFFF00");
            }
            else {
                hbox.setStyle("-fx-background-color: #FFFFFF");
            }
        });

        // put in scene and display
        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
