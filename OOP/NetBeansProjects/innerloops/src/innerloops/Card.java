/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innerloops;

/**
 *
 * @author Sean-
 */
public class Card {
    private int value;
    private CardSuit suit;

    public Card(int value, CardSuit suit) {
        
        setValue(value);
        setSuit(suit);
        
    }
    
    public String toString() {
        return value + " " + suit;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }
    
    
}
