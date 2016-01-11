/*
  Niels Graham, Shamaul Dilmohamed 
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-11
*/


public class Card {
    private String value;
    private String cardLine = "\t|               |\n";

    /*
    private final String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    // the list of values that a card can hold

    private final String[] suits = {"diamond", "club", "heart", "spade"};
    // the list of suits a card can have
    */ //all constants will go in Deck

    public Card(String val, String suit) {
	value = "\t_________________\n";
	value += "\t|" + suit + cardLine.substring(suit.length()+2);
	for (int i = 0; i < 4; i++) {
	    value += cardLine;
	}
	value += cardLine.substring(0,cardLine.length()-val.length()-9) + val + cardLine.substring(cardLine.length()-val.length()-8);
	for (int i = 0; i < 4; i++) {
	    value += cardLine;
	}
	value += cardLine.substring(0,cardLine.length()-suit.length()-2) + suit + "|\n";
	value += "\t";
	for (int x = 0; x < 17; x++) {
	    value += Character.toString((char)175); // aesthetics 
	}
	value += "\n";
	    }

    public String getCard() {
	return value;
    }

    public static void main(String[] args) {
	Card sham = new Card("9","heart");
	System.out.println("Shamaul:");	
	System.out.println(sham.getCard());
	Card niels = new Card ("A","spade");
	System.out.println("Niels:");	
	System.out.println(niels.getCard());
	Card kate = new Card ("3","diamond");
	System.out.println("Kate:");	
	System.out.println(kate.getCard());
	Card brown = new Card ("K","club");
	System.out.println("Mr. Brown:");	
	System.out.println(brown.getCard());
    }
}
