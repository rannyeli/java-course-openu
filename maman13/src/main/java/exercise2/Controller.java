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

        if (checkIfWin(playerTurn)) {
            displayMessage("Winner!");
        } else {
            passTurn();
        }
    }

    private boolean checkIfWin(int player) {
        // horizontalCheck
        for (int j = 0; j < ROWS - 3; j++) {
            for (int i = 0; i < COLS; i++) {
                if (boardMat[i][j] == player && boardMat[i][j + 1] == player && boardMat[i][j + 2] == player
                        && boardMat[i][j + 3] == player) {
                    return true;
                }
            }
        }
        // verticalCheck
        for (int i = 0; i < COLS - 3; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (boardMat[i][j] == player && boardMat[i + 1][j] == player && boardMat[i + 2][j] == player
                        && boardMat[i + 3][j] == player) {
                    return true;
                }
            }
        }
        // ascendingDiagonalCheck
        for (int i = 3; i < COLS; i++) {
            for (int j = 0; j < ROWS - 3; j++) {
                if (boardMat[i][j] == player && boardMat[i - 1][j + 1] == player && boardMat[i - 2][j + 2] == player
                        && boardMat[i - 3][j + 3] == player)
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int i = 3; i < COLS; i++) {
            for (int j = 3; j < ROWS; j++) {
                if (boardMat[i][j] == player && boardMat[i - 1][j - 1] == player && boardMat[i - 2][j - 2] == player
                        && boardMat[i - 3][j - 3] == player)
                    return true;
            }
        }
        return false;
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
        passTurn();
    }

}
