package exercise2;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller {

    private static final int N = 30;
    private static final int PERCENTS = 10;
    private static final int NUM_OF_CELLS_TO_FILL = N * N * PERCENTS / 100;
    private static final int CELL_SIZE = 10;
    private static final Canvas _canvas = new Canvas(CELL_SIZE * N, CELL_SIZE * N);

    private Random _rand;
    private GraphicsContext _gc;

    @FXML
    private VBox layout;

    @FXML
    public void initialize() {
        _rand = new Random();
        _gc = _canvas.getGraphicsContext2D();
    }

    /**
     * fill button handler - creates a 10% percets filled in black martix
     * 
     * @param event
     */
    @FXML
    void onButtonClick(ActionEvent event) {
        boolean[][] isFilled = new boolean[N][N];
        _gc.setStroke(Color.BLACK);
        _gc.setFill(Color.WHITE);

        // create grid
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                _gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                _gc.strokeRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                isFilled[x][y] = false;
            }
        }

        // fill 10% of the cell randomally
        int count = 0, randx, randy;
        _gc.setFill(Color.BLACK);
        while (count < NUM_OF_CELLS_TO_FILL) {
            randx = _rand.nextInt(N);
            randy = _rand.nextInt(N);
            if (!isFilled[randx][randy]) {
                _gc.fillRect(randx * CELL_SIZE, randy * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                isFilled[randx][randy] = true;
                count++;
            }
        }

        // replace the canvas on the window layout
        layout.getChildren().remove(_canvas);
        layout.getChildren().add(_canvas);
    }

}
