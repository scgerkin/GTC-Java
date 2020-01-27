package exoticmoves;

import java.util.ArrayList;

import exoticmoves.ExoticMoves.ListClickEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ListPane holds the GUI pane for displaying the list of cars in the Inventory
 */
public class ListPane extends DisplayArea {
    // gui items
    private VBox root;
    private ScrollPane scrollPane;
    private VBox listContainer;
    private ObservableList<HBox> listItems;

    // event handler for clicking on a list item
    private ListClickEvent event;

    // Filters object for holding currently selected filters
    private Filters filters;

    // Car list from inventory
    ArrayList<Car> carList;

    /**
     * Default constructor
     * @param width
     * @param height
     * @param carList
     * @param event Event Handler for clicking on the list items
     * @param filters
     */
    public ListPane(Double width, Double height, ArrayList<Car> carList,
                    ListClickEvent event, Filters filters)
    {
        super(width, height);
        this.carList = carList;
        this.event = event;
        this.filters = filters;
        initialize();
    }

    /**
     * Method inits all panes within panes as new items before building the list
     */
    protected void initialize() {
        // init members
        root = new VBox();// root holds the ScrollPane and allows super to work
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        listItems = FXCollections.observableArrayList();
        listContainer = new VBox();

        root.getChildren().add(scrollPane);
        scrollPane.setContent(listContainer);
        refreshDisplayList();
        super.setContainer(root);
        super.setCssClass("listPane");
    }

    /**
     * Method refreshes the display list
     */
    public void refreshDisplayList() {
        // clean list and container
        listContainer.getChildren().clear();
        listItems.clear();

        // add items to list
        for (Car car : carList) {
            if (filters.allow(car)) {
                HBox itemDisplay = createItemDisplay(car);
                itemDisplay.setId(car.getId());
                itemDisplay.getStyleClass().add("listItem");
                itemDisplay.setMinSize(483, 100);
                listItems.add(itemDisplay);
            }
        }
        if (listItems.isEmpty()) {
            Text text = new Text("No matches for search criteria found.");
            listContainer.getChildren().add(new HBox(text));
        }
        // add list items back to container
        listContainer.getChildren().addAll(listItems);
    }

    /**
     * Method creates an HBox with @param car information and handling
     * @return HBox
     */
    private HBox createItemDisplay(Car car) {
        // create elements to display car details
        Text text = new Text(
            car.getColor() + " " +
            car.getBrand() +
            (car.isConvertible() ? " Convertible": "") + "\n" +
            "Starts at $" + car.getPrice() + ",000"
        );
        text.getStyleClass().add("listItem");

        // create ImageView for car
        ImageView itemImage = new ImageView(car.getImage());
        itemImage.setFitHeight(75);
        itemImage.setFitWidth(150);

        // box to hold
        HBox itemContainer = new HBox();
        itemContainer.getChildren().addAll(itemImage, text);

        // add event for box clicking
        itemContainer.addEventHandler(MouseEvent.MOUSE_CLICKED, event);

        return itemContainer;
    }

    /**
     * Builds the pane based on a @param newList object
     */
    public void applyNewList(ArrayList<Car> newList) {
        this.carList = newList;
        refreshDisplayList();
    }
}
