package exercise1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Controller {

    private Line line;
    private Circle circle;
    private Rectangle rectangle;
    private String type = "line";
    Group group = new Group();

    @FXML
    private Pane drawpane;

    @FXML
    public void initialize() {
        drawpane.getChildren().add(group);
    }

    @FXML
    void onLineClick(ActionEvent event) {
        type = "line";
    }

    @FXML
    void onCircleClick(ActionEvent event) {
        type = "circle";
    }

    @FXML
    void onRectangleClick(ActionEvent event) {
        type = "rectangle";
    }

    @FXML
    void onMousePressed(MouseEvent event) {
        switch (type) {
            case "line":
                line = new Line();
                line.setStrokeWidth(2);
                line.setFill(Color.BLACK);
                line.setStartX(event.getX());
                line.setStartY(event.getY());
                break;
            case "circle":
                circle = new Circle();
                circle.setStrokeWidth(2);
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                break;
            case "rectangle":
                rectangle = new Rectangle();
                rectangle.setStrokeWidth(2);
                rectangle.setX(event.getX());
                rectangle.setY(event.getY());
                break;
        }
    }

    @FXML
    void onMouseReleased(MouseEvent event) {
        switch (type) {
            case "line":
                line.setEndX(event.getX());
                line.setEndY(event.getY());
                group.getChildren().add(line);
                break;
            case "circle":
                circle.setRadius(event.getX() - circle.getCenterX());
                group.getChildren().add(circle);
                break;
            case "rectangle":
                rectangle.setWidth(event.getX() - rectangle.getX());
                rectangle.setHeight(event.getY() - rectangle.getY());
                group.getChildren().add(rectangle);
                break;
        }
    }
}
