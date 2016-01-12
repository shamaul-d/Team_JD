/*
  Niels Graham, Shamaul Dilmohamed 
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-11
*/


public class Card {
    private String value;
    private String cardLine = "\t|               |\n"; //body of each playing card

    public Card(String val, String suit) {
	value = "\t_________________\n"; //top of card
	value += "\t|" + suit + cardLine.substring(suit.length()+2); //suit name in upper left hand corner
	for (int i = 0; i < 4; i++) {
	    value += cardLine; //body of card
	}
	value += cardLine.substring(0,cardLine.length()-val.length()-9) + val + cardLine.substring(cardLine.length()-val.length()-8); // value in middle of card
	for (int i = 0; i < 4; i++) {
	    value += cardLine; //body of card
	}
	value += cardLine.substring(0,cardLine.length()-suit.length()-2) + suit + "|\n"; //suit at bottom right hand corner
	value += "\t";
	for (int x = 0; x < 17; x++) { //just so happens that each line is 17 characters long
	    value += Character.toString((char)175); // aesthetics; bottom of card 
	}
	value += "\n";
	    }

    public String toString() {
	return value;
    }

    public static void main(String[] args) {
	Card sham = new Card("9","heart");
	System.out.println("Shamaul:");	
	System.out.println(sham);
	Card niels = new Card ("A","spade");
	System.out.println("Niels:");	
	System.out.println(niels);
	Card kate = new Card ("3","diamond");
	System.out.println("Kate:");	
	System.out.println(kate);
	Card brown = new Card ("K","club");
	System.out.println("Mr. Brown:");	
	System.out.println(brown);
    }
}
