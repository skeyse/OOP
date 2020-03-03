package gameofwar;

/**
 *
 * @author Sean-
 */
public class GameOfWar {

    private Player playerOne;
    private Player playerTwo;
    private Deck deck;
    
    public GameOfWar() {
        playerOne = new Player("Player", "One");
        playerTwo = new Player("Player", "Two");
        deck = new Deck();
    }
    
    public static void main(String[] args) {
        GameOfWar gameOfWar = new GameOfWar();
        
        gameOfWar.start();
        
        
        
    }
    
    public void start() {
        dealCards();
    }
    
    private void dealCards() {
        for(int i = deck.getSize(); i > 0; i--) {
            if(i%2 == 0) {
                playerOne.addCard(deck.removeCard());
            }
            else {
                playerTwo.addCard(deck.removeCard());
            }
        }
    }
    
}
