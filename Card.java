/*
  Niels Graham, Shamaul Dilmohamed 
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-11
*/


public class Card {
    private String value;
    private String cardOutline ="\t_________________\n" +  
	                        "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|               |\n" +
                                "\t|_______________|\n";
    







    private final String[] vals = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    // the list of values that a card can hold

    private final String[] suits = {"diamond", "clubs", "hearts", "spades"};
    // the list of suits a card can have
 

    public static void main(String[] args) {
	Card sham = new Card();
	System.out.println(sham.cardOutline);
    }
}
