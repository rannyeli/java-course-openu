package exercise2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {

    private final int ROWS = 6;
    private final int COLS = 7;
    private int playerTurn;
    private int[][] boardMat = new int[COLS][ROWS];

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label blueLabel;

    @FXML
    private Label redLabel;

    @FXML
    public void initialize() {
        gameBoard.setGridLinesVisible(true);
        passTurn();
    }

    private int getNextAvailableRowForCol(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (boardMat[col][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private void displayMessage(String message) {
        if (playerTurn == 1) {
            redLabel.setText(message);
            blueLabel.setText("");
        } else {
            blueLabel.setText(message);
            redLabel.setText("");
        }
    }

    private void passTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
            displayMessage("Blue turn");
        } else {
            playerTurn = 1;
            displayMessage("Red turn");
        }
    }

    private void insertDisc(int col) {
        int row = getNextAvailableRowForCol(col);
        if (row == -1) {
            displayMessage("Column " + (col + 1) + " is full");
            return;
        }
        boardMat[col][row] = playerTurn;
        Circle disc = new Circle(45, playerTurn == 1 ? Color.RED : Color.BLUE);
        gameBoard.add(disc, col, ROWS - row - 1);
        passTurn();
    }

    @FXML
    void handleColOne(ActionEvent event) {
        insertDisc(0);
    }

    @FXML
    void handleColTwo(ActionEvent event) {
        insertDisc(1);
    }

    @FXML
    void handleColThree(ActionEvent event) {
        insertDisc(2);
    }

    @FXML
    void handleColFour(ActionEvent event) {
        insertDisc(3);
    }

    @FXML
    void handleColFive(ActionEvent event) {
        insertDisc(4);
    }

    @FXML
    void handleColSix(ActionEvent event) {
        insertDisc(5);
    }

    @FXML
    void handleColSeven(ActionEvent event) {
        insertDisc(6);
    }

    @FXML
    void handleClear(ActionEvent event) {
        boardMat = new int[COLS][ROWS];
        gameBoard.getChildren().clear();
        gameBoard.setGridLinesVisible(false);
        gameBoard.setGridLinesVisible(true);
    }

}
