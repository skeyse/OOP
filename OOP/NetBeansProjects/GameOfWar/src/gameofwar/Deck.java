package gameofwar;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private List<Card> cards;
    
    public Deck() {
        cards = new LinkedList();
        initialize();
    }
    
    public Card removeCard() {
        Card card = cards.remove(cards.size() - 1);
        return card;
    }
    
    public int getSize() {
        return cards.size();
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    private void initialize() {
        for(Suit suit: Suit.values()) {
            for(Value value: Value.values()) {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }
}
