/*
  Niels Graham, Shamaul Dilmohamed 
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-11
*/


public class Card {
    private String value; //Printed Card (Ascii)
    private String pin; //Stored Suit
    private String rank; //ex: king, jack, seven 
    private String cardLine = "\t|               |\n"; //body of each playing card

    public Card(String val, String suit) {
	value = "\t_________________\n"; //top of card
	value += "\t|" + suit + cardLine.substring(suit.length()+2); //suit name in upper left hand corner
	for (int i = 0; i < 4; i++) {
	    value += cardLine; //body of card
	}
	if (! val.equals("10")) {
		value += cardLine.substring(0,cardLine.length()-val.length()-9) + val + cardLine.substring(cardLine.length()-val.length()-8); // value in middle of card
	    }
	    else {
		value += cardLine.substring(0,cardLine.length()-val.length()-9) + val + cardLine.substring(cardLine.length()-val.length()-7); 
		// fixes bug when value is 10
	    }
	for (int i = 0; i < 4; i++) {
	    value += cardLine; //body of card
	}
	value += cardLine.substring(0,cardLine.length()-suit.length()-2) + suit + "|\n"; //suit at bottom right hand corner
	value += "\t";
	for (int x = 0; x < 17; x++) { //just so happens that each line is 17 characters long
	    value += Character.toString((char)175); // aesthetics; bottom of card 
	}
	value += "\n";
	pin = suit;
	rank = val;
    }
    //=============Get Functions====================
    public String toString() {
	return value;
    }
    
    public String getRank(){
	return rank;
    }

    public String getSuit(){
	return pin;
    }
    //===============idk what's here=================
    public static int toInt(String x){ //turns the card rank into ints, used in compareTo
	int retInt;
	if (x.equals("J"))
	    retInt = 11;
	else if (x.equals("Q"))
	    retInt = 12;
	else if (x.equals("K"))
	    retInt = 13;
	else if (x.equals("A"))
	    retInt = 14;
	else
	    retInt = Integer.parseInt(x);
    return retInt;
    }

    public int suitToInt(String x){//used in compareTo
	int retInt = 0; 
	if (x.equals("diamond"))
	    retInt = 1;
	if (x.equals("club"))
	    retInt = 2;
	if (x.equals("heart"))
	    retInt = 3;
	if (x.equals("spade"))
	    retInt = 4;
	return retInt;
    }
    
    public int compareTo(Card a){
	int retInt = 0;
	if (toInt(this.getRank()) == toInt(a.getRank())){
	    if (suitToInt(this.getSuit()) > suitToInt(a.getSuit()))
		retInt = 1;
	}
	else if (toInt(this.getRank()) >  toInt(a.getRank()))
	    retInt = 1;
	else if (toInt(this.getRank()) <  toInt(a.getRank()))
	    retInt = -1;
	return retInt;
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
