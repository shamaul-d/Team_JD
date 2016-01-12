/*
  Niels Graham, Shamaul Dilmohamed
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-12
*/

public class Deck {

    private Card[][] deck;

    private final String[] vals = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    // the list of values that a card can hold

    private final String[] suits = {"diamond", "club", "heart", "spade"};
    // the list of suits a card can have

    public Deck(){
	deck = new Card[4][13]; 
	//number of suits and values
	for (int x = 0; x < 4; x++) {
	    for (int y = 0; y < 13; y++) {
		deck[x][y] = new Card(vals[y],suits[x]);
		// creates a card for each array position
	    }
	}
    }

    public void shuffle() {
	int tempx;
	int tempy;
	for (int x = 0; x < deck.length; x++) {
	    for (int y = 0; y < deck[x].length; y++) { 
		// goes through every position once; everything shuffled
		tempx = (int)(Math.random() * deck.length);
		tempy = (int)(Math.random() * deck[x].length);
		//tempx and tempy ensure randomness and a complete swap
		Card hold = deck[x][y];
		deck[x][y] = deck[tempx][tempy];
		deck[tempx][tempy] = hold;
	    }
	}
    }

    public String toString(){
	String ans = "";
	for (Card[] x : deck) {
	    for (Card y : x) {
		ans += y;
		ans += "\n";
	    }
	}
	return ans;
	//returns whole deck, will not be used in game itself
    }

    public static void main(String[] args) {
	Deck ash = new Deck();
	System.out.println("Newly opened deck:");
	System.out.println(ash);
	ash.shuffle();
	System.out.println("Shuffled deck:");
	System.out.println(ash);
    }
}
