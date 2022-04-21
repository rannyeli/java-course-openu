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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {

    private Line line;
    private Circle circle;
    private Rectangle rectangle;
    private String type;
    private Color color;
    private final Group group = new Group();
    private final ToggleGroup tgroup = new ToggleGroup();

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

    @FXML
    public void initialize() {
        drawpane.getChildren().add(group);
        comboBox.setItems(FXCollections.observableArrayList("Line", "Circle", "Rectangle"));
        comboBox.getSelectionModel().selectFirst();
        fillRadioBtn.setToggleGroup(tgroup);
        emptyRadioBtn.setToggleGroup(tgroup);
        fillRadioBtn.setSelected(true);
        type = comboBox.getValue();
        color = colorPicker.getValue();
    }

    @FXML
    void handleShapeChange(ActionEvent event) {
        type = comboBox.getValue();
    }

    @FXML
    void handleColorChange(ActionEvent event) {
        color = colorPicker.getValue();
    }

    private void initShape(Shape shape) {
        shape.setStrokeWidth(2);
        shape.setStroke(color);
        if (fillRadioBtn.isSelected())
            shape.setFill(color);
        else
            shape.setFill(Color.TRANSPARENT);
    }

    @FXML
    void handleMousePressed(MouseEvent event) {
        switch (type) {
            case "Line":
                line = new Line();
                initShape(line);
                line.setStartX(event.getX());
                line.setStartY(event.getY());
                break;
            case "Circle":
                circle = new Circle();
                initShape(circle);
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                break;
            case "Rectangle":
                rectangle = new Rectangle();
                initShape(rectangle);
                rectangle.setX(event.getX());
                rectangle.setY(event.getY());
                break;
        }
    }

    @FXML
    void handleMouseReleased(MouseEvent event) {
        switch (type) {
            case "Line":
                line.setEndX(event.getX());
                line.setEndY(event.getY());
                group.getChildren().add(line);
                break;
            case "Circle":
                circle.setRadius(Math.abs(event.getX() - circle.getCenterX()));
                group.getChildren().add(circle);
                break;
            case "Rectangle":
                rectangle.setWidth(Math.abs(event.getX() - rectangle.getX()));
                rectangle.setHeight(Math.abs(event.getY() - rectangle.getY()));
                group.getChildren().add(rectangle);
                break;
        }
    }

    @FXML
    void handleUndoClick(ActionEvent event) {
        int size = group.getChildren().size();
        if (size > 0) {
            group.getChildren().remove(size - 1);
        }
    }

    @FXML
    void handleClearClick(ActionEvent event) {
        group.getChildren().clear();
    }
}
