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

    private void drawShape() {
        switch (type) {
            case "Line":
                line = new Line(x1, y1, x2, y2);
                initShape(line);
                group.getChildren().add(line);
                break;
            case "Circle":
                circle = new Circle(x1, y1, Math.abs(x2 - x1));
                initShape(circle);
                group.getChildren().add(circle);
                break;
            case "Rectangle":
                double w = Math.abs(x2 - x1);
                double h = Math.abs(y2 - y1);
                double x = Math.abs(Math.max(x1, x2) - w);
                double y = Math.min(y1, y2);
                rectangle = new Rectangle(x, y, w, h);
                initShape(rectangle);
                group.getChildren().add(rectangle);
                break;
        }
    }

    @FXML
    void handleMousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
    }

    @FXML
    void handleMouseReleased(MouseEvent event) {
        x2 = event.getX();
        y2 = event.getY();
        drawShape();
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
