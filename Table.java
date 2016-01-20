public class Table {

    private int[] main; // holds current bet and size of pot

    private int[] side; // same qualities

    private boolean[] bets; // holds data if players are betting or not

    private Card[] river; // holds the cards for the river
    
    private Deck deck; // holds a deck of cards
    
    private Player[] plays; // players in game

    public Table(Player[] gamblers){
	deck = new Deck();
	main = new int[2];
	main[0] = 10; //main[1] will get value of 0
	side = new int[2]; // both indices will get val of 0
	bets = new boolean[gamblers.length];
	for (int i = 0; i < bets.length; i++) {
	    bets[i] = true;
	}
	river = setRiver();
	plays = gamblers;
    }
    
    public Card[] setRiver(){
	Card[] ans; 
	//deck.shuffle();
	ans = new Card[]{deck.getCard(0,0), deck.getCard(0,1), deck.getCard(0,2), deck.getCard(0,3), deck.getCard(0,4)};

	return ans;
    }
    
    public Card retCard(int ctr){
	return river[ctr];
    }
    
    public Deck getDeck() {
	return deck;
    }

    public boolean isFolded(Player c) {
	for (int x = 0; x < plays.length; x++) {
	    if (c.toString().equals(plays[x].toString())) {
		return bets[x];
	    }
	}
	return false;
    }

    public void fold(Player a) {
	for (int x = 0; x < plays.length; x++) {
	    if (a.toString().equals(plays[x].toString())) {
		bets[x] = false;
		break;
	    }
	}
    }
    
    public static void main(String[] args) {
	Player niels = new Player();
	Table asher = new Table(new Player[]{niels});
	asher.setRiver();
	for (int i = 0; i < 5; i++) {
	    System.out.println(asher.retCard(i));
	}
	System.out.println(asher.isFolded(niels));
	asher.fold(niels);
	System.out.println(asher.isFolded(niels));
    }
}    
