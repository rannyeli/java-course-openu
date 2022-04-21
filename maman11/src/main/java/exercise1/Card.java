package exercise1;

/**
 * class which represents a card
 */
public class Card {
    private String _face;
    private String _suit;
    private int _value;

    /**
     * constructor
     * 
     * @param face:  card's face
     * @param suit:  card's suite
     * @param value: card's value
     */
    public Card(String face, String suit, int value) {
        this._face = face;
        this._suit = suit;
        this._value = value;
    }

    /**
     * @return card's face
     */
    public String get_face() {
        return _face;
    }

    /**
     * @return card's suit
     */
    public String get_suit() {
        return _suit;
    }

    /**
     * @return card's value
     */
    public int get_value() {
        return _value;
    }

    @Override
    public String toString() {
        return _face + " " + _suit;
    }
}
