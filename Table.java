public class Table {

    private int[] main; // holds current bet and size of pot

    private int[] side; // same qualities

    private boolean[] bets; // holds data if players are betting or not

    private Card[] river; // holds the cards for the river
    
    private Deck deck; // holds a deck of cards

    public Table(Player[] gamblers){
	deck = new Deck();
	deck.shuffle(); //shuffles cards to get ready for hand
	main = {10,0}
	side = {0,0};
	bets = gamblers.length;
	for (int i = 0; i < bets.length; i++) {
	    bets[i] = true;
	}
    }
	
}    
