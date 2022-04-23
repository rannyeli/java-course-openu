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
        passTurn();
    }

    /**
     * search for the next available cell in the selected column.
     * 
     * @param col: selected column.
     * @return row of the available cell, in there is no available cell returns -1.
     */
    private int getNextAvailableRowForCol(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (boardMat[col][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * displays a message for the player whose turn to play.
     * 
     * @param message: text to display.
     */
    private void displayMessage(String message) {
        if (playerTurn == 1) {
            redLabel.setText(message);
            blueLabel.setText("");
        } else {
            blueLabel.setText(message);
            redLabel.setText("");
        }
    }

    /**
     * pass the turn to the second player.
     */
    private void passTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
            displayMessage("Blue turn");
        } else {
            playerTurn = 1;
            displayMessage("Red turn");
        }
    }

    /**
     * check if a player won.
     * 
     * @param player: player to check.
     * @return true if he does, false if not.
     */
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

    /**
     * insert new disc to board with the player's color.
     * 
     * @param col: selected column to insert to.
     */
    private void insertDisc(int col) {
        int row = getNextAvailableRowForCol(col);
        if (row == -1) {
            displayMessage("Column " + (col + 1) + " is full");
            return;
        }
        boardMat[col][row] = playerTurn;
        Circle disc = new Circle(45, playerTurn == 1 ? Color.RED : Color.BLUE); // create new disc
        gameBoard.add(disc, col, ROWS - row - 1); // add to board

        if (checkIfWin(playerTurn)) {
            displayMessage("Winner!");
        } else {
            passTurn();
        }
    }

    /**
     * handle column 1 selected
     * 
     * @param event
     */
    @FXML
    void handleColOne(ActionEvent event) {
        insertDisc(0);
    }

    /**
     * handle column 2 selected
     * 
     * @param event
     */
    @FXML
    void handleColTwo(ActionEvent event) {
        insertDisc(1);
    }

    /**
     * handle column 3 selected
     * 
     * @param event
     */
    @FXML
    void handleColThree(ActionEvent event) {
        insertDisc(2);
    }

    /**
     * handle column 4 selected
     * 
     * @param event
     */
    @FXML
    void handleColFour(ActionEvent event) {
        insertDisc(3);
    }

    /**
     * handle column 5 selected
     * 
     * @param event
     */
    @FXML
    void handleColFive(ActionEvent event) {
        insertDisc(4);
    }

    /**
     * handle column 6 selected
     * 
     * @param event
     */
    @FXML
    void handleColSix(ActionEvent event) {
        insertDisc(5);
    }

    /**
     * handle column 7 selected
     * 
     * @param event
     */
    @FXML
    void handleColSeven(ActionEvent event) {
        insertDisc(6);
    }

    /**
     * handle clear table - reset game.
     * 
     * @param event
     */
    @FXML
    void handleClear(ActionEvent event) {
        boardMat = new int[COLS][ROWS];
        gameBoard.getChildren().clear();
        gameBoard.setGridLinesVisible(false);
        gameBoard.setGridLinesVisible(true);
        passTurn();
    }

}
