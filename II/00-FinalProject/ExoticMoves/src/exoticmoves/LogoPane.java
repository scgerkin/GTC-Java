package exoticmoves;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * LogoPane creates a pane for displaying the logo
 */
public class LogoPane extends DisplayArea {

    public LogoPane(Double width, Double height) {
        super(width, height);

        // creates embossed text effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(2);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setColor(Color.web("#555555"));

        Text text = new Text("Exotic Moves Luxury Car Company");
        text.getStyleClass().add("logoPane");
        text.setEffect(dropShadow);

        HBox box = new HBox(text);
        box.getStyleClass().add("logoPane");

        super.setContainer(box);
        super.setCssClass("logoPane");
    }
}
