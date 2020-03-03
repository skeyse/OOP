package gameofwar;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player implements Person {
    private final String firstName;
    private final String lastName;
    private List<Card> cards;
    private List<Card> sidePile;
    
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cards = new LinkedList();
        this.sidePile = new LinkedList();
        
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }
    @Override
    public String getLastName() {
        return lastName;
    }
    
    public Card playCard() {
        return cards.remove(0);
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void collectCards(List<Card> cards) {
        for(Card card: cards) {
            sidePile.add(card);
        }
    }
    
    public int getCardCount() {
        return cards.size() + sidePile.size();
    }
    
    private void shuffleSidePile() {
        Collections.shuffle(sidePile);
        for(Card card: sidePile) {
            cards.add(card);
        }
        sidePile.clear();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName).append(' ').append(lastName).append('\n');
        builder.append("# of Cards: ").append(cards.size()).append('\n');
        builder.append("Side Pile Size: ").append(sidePile.size());
        
        return builder.toString();
    }
}
