package exercise1;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class Controller {

    private static final int TO_DEAL_WHEN_MILHAMA = 3;
    private static final int MAX_ROUNDS = 1000;

    /**
     * Handler of click on the start button.
     * The gameplay
     * 
     * @param event
     */
    @FXML
    void onStartClick(ActionEvent event) {
        // init dialog
        Dialog<String> dialog = new Dialog<String>();
        ButtonType type = new ButtonType("Next Round", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);

        // create a new deck
        DeckOfCards deck = new DeckOfCards();
        // shuffle
        deck.shuffle();
        // split deck for two players
        DeckOfCards[] decks = deck.split();
        DeckOfCards player1Deck = decks[0];
        DeckOfCards player2Deck = decks[1];

        int round = 1, toDealNext = 1, totalOnTable;
        String decksStatus, roundStatus;
        ArrayList<Card> player1CardsOnTable = new ArrayList<Card>();
        ArrayList<Card> player2CardsOnTable = new ArrayList<Card>();
        Card player1Top, player2Top;

        // start the game
        while (player1Deck.getAmount() > 0 && player2Deck.getAmount() > 0 && round < MAX_ROUNDS) {
            // each player puts cards on the table
            for (int i = 0; i < toDealNext; i++) {
                if (player1Deck.getAmount() > 0 && player2Deck.getAmount() > 0) {
                    player1CardsOnTable.add(player1Deck.dealCard());
                    player2CardsOnTable.add(player2Deck.dealCard());
                }
            }
            player1Top = player1CardsOnTable.get(player1CardsOnTable.size() - 1);
            player2Top = player2CardsOnTable.get(player2CardsOnTable.size() - 1);
            toDealNext = 1;
            totalOnTable = player1CardsOnTable.size() + player2CardsOnTable.size();
            decksStatus = "cards left: " + player1Deck.getAmount() + "\t\t\t\t\tcards left: "
                    + player2Deck.getAmount();
            dialog.setTitle("Round " + round);

            // check who wins the round
            if (player1Top.get_value() > player2Top.get_value()) {
                player1Deck.collect(player1CardsOnTable);
                player1Deck.collect(player2CardsOnTable);
                player1CardsOnTable.clear();
                player2CardsOnTable.clear();
                roundStatus = "player1 takes all " + totalOnTable + " cards.";
            } else if (player1Top.get_value() < player2Top.get_value()) {
                player2Deck.collect(player1CardsOnTable);
                player2Deck.collect(player2CardsOnTable);
                player1CardsOnTable.clear();
                player2CardsOnTable.clear();
                roundStatus = "player2 takes all " + totalOnTable + " cards.";
            } else {
                toDealNext = TO_DEAL_WHEN_MILHAMA;
                roundStatus = "It's a MILHAMA! each draws 3 cards.";
            }

            // prepare round summary into the dialog content
            dialog.setContentText(
                    "player1:\t\t\t\t\t\tplayer2:\n" + "card: " + player1Top.toString() + "\t\t\tcard: "
                            + player2Top.toString() + "\n" + decksStatus + "\n\n" + roundStatus);
            dialog.showAndWait();

            round++;
        }

        // check who is the game winner
        if (player1Deck.getAmount() > player2Deck.getAmount()) {
            dialog.setContentText("player1 is the winner!");
        } else if (player1Deck.getAmount() < player2Deck.getAmount()) {
            dialog.setContentText("player2 is the winner!");
        } else {
            dialog.setContentText("It's a tie!");
        }
        dialog.showAndWait();
    }

}