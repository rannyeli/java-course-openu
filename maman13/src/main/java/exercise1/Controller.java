package exercise1;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {

    private Line line;
    private Ellipse circle;
    private Rectangle rectangle;
    private String type;
    private Color color;
    private final Group shapesGroup = new Group();
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private double x1, y1, x2, y2;

    @FXML
    private Pane drawpane;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private RadioButton fillRadioBtn;
    @FXML
    private RadioButton emptyRadioBtn;

    /**
     * initialize screen properties
     */
    @FXML
    public void initialize() {
        drawpane.getChildren().add(shapesGroup); // add shapes group to screen
        comboBox.setItems(FXCollections.observableArrayList("Line", "Circle", "Rectangle")); // set optional shapes
        comboBox.getSelectionModel().selectFirst(); // set first shape in list as default
        fillRadioBtn.setToggleGroup(toggleGroup); // add radio button to toggle group
        emptyRadioBtn.setToggleGroup(toggleGroup);
        fillRadioBtn.setSelected(true); // set fill radio button selected as default
        type = comboBox.getValue();
        color = colorPicker.getValue();
    }

    /**
     * shape selector handler - save selected shape on change
     * 
     * @param event
     */
    @FXML
    void handleShapeChange(ActionEvent event) {
        type = comboBox.getValue();
    }

    /**
     * color selector handler - save selected color on change
     * 
     * @param event
     */
    @FXML
    void handleColorChange(ActionEvent event) {
        color = colorPicker.getValue();
    }

    /**
     * prepare shape color properties for drawing
     * 
     * @param shape
     */
    private void initShape(Shape shape) {
        shape.setStrokeWidth(2);
        shape.setStroke(color);
        if (fillRadioBtn.isSelected())
            shape.setFill(color);
        else
            shape.setFill(Color.TRANSPARENT);
    }

    /**
     * handle shape drawing, draw shape as requested and add to screen
     */
    private void drawShape() {
        double x, y, w, h, rx, ry;
        // create the requested shape and add to screen
        switch (type) {
            case "Line":
                line = new Line(x1, y1, x2, y2);
                initShape(line);
                shapesGroup.getChildren().add(line);
                break;
            case "Circle":
                rx = Math.abs(x2 - x1) / 2;
                ry = Math.abs(y2 - y1) / 2;
                x = Math.abs(Math.max(x1, x2) - Math.abs(x2 - x1)) + rx;
                y = Math.min(y1, y2) + ry;
                circle = new Ellipse(x, y, rx, ry);
                initShape(circle);
                shapesGroup.getChildren().add(circle);
                break;
            case "Rectangle":
                w = Math.abs(x2 - x1);
                h = Math.abs(y2 - y1);
                x = Math.abs(Math.max(x1, x2) - w);
                y = Math.min(y1, y2);
                rectangle = new Rectangle(x, y, w, h);
                initShape(rectangle);
                shapesGroup.getChildren().add(rectangle);
                break;
        }
    }

    /**
     * save event's coordinates when mouse pressed
     * 
     * @param event
     */
    @FXML
    void handleMousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
    }

    /**
     * save event's coordinates when mouse released, then start drawing
     * 
     * @param event
     */
    @FXML
    void handleMouseReleased(MouseEvent event) {
        x2 = event.getX();
        y2 = event.getY();
        drawShape();
    }

    /**
     * handle undo button click - remove last shape from screen
     * 
     * @param event
     */
    @FXML
    void handleUndoClick(ActionEvent event) {
        int size = shapesGroup.getChildren().size();
        if (size > 0) {
            shapesGroup.getChildren().remove(size - 1);
        }
    }

    /**
     * handle clear button click - remove all shapes from screen
     * 
     * @param event
     */
    @FXML
    void handleClearClick(ActionEvent event) {
        shapesGroup.getChildren().clear();
    }
}
