package exercise1;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * class which represents a deck of cards.
 */
public class DeckOfCards {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;
    private static final String[] FACES = { "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Jack",
            "Queen", "King", "Ace" };
    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };

    private ArrayList<Card> _cards;

    /**
     * constructor - creates cards.
     */
    public DeckOfCards() {
        _cards = new ArrayList<Card>();
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            _cards.add(i, new Card(FACES[i % 13], SUITS[i / 13], i % 13));
        }
    }

    /**
     * seccond constructor, fills deck with a given cards
     * 
     * @param cards: cards to fill
     */
    public DeckOfCards(ArrayList<Card> cards) {
        _cards = cards;
    }

    /**
     * shuffles all cards in deck
     */
    public void shuffle() {
        for (int first = 0; first < _cards.size(); first++) {
            int second = randomNumbers.nextInt(_cards.size());

            Card temp = _cards.get(first);
            _cards.set(first, _cards.get(second));
            _cards.set(second, temp);
        }
    }

    /**
     * splits this deck into two new decks
     * 
     * @return array with two decks
     */
    public DeckOfCards[] split() {
        ArrayList<Card> list1 = new ArrayList<Card>();
        ArrayList<Card> list2 = new ArrayList<Card>();
        int size = _cards.size();
        list1.addAll(_cards.subList(0, size / 2));
        list2.addAll(_cards.subList(size / 2, size));
        DeckOfCards[] decks = { new DeckOfCards(list1), new DeckOfCards(list2) };
        return decks;
    }

    /**
     * peeks one card from the top of the deck
     * 
     * @return the card
     */
    public Card dealCard() {
        if (_cards.size() > 0) {
            return _cards.remove(_cards.size() - 1);
        }
        return null;
    }

    /**
     * add cards to the bottom of the deck
     * 
     * @param cards: cards to add
     */
    public void collect(ArrayList<Card> cards) {
        _cards.addAll(0, cards);
    }

    /**
     * get the number of cards in the deck
     * 
     * @return number of cards
     */
    public int getAmount() {
        return _cards.size();
    }

    @Override
    public String toString() {
        return _cards.toString();
    }
}
