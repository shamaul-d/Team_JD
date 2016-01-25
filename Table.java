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
	river = new Card[5];
	plays = gamblers;
    }
    
    public void setRiver(){
	deck.shuffle();
	river = new Card[]{deck.getCard(0,0), deck.getCard(0,1), deck.getCard(0,2), deck.getCard(0,3), deck.getCard(0,4)};
    }

    public void deal() {
	int num = plays.length;
	if (num > 6) { //max 10 players
	    for (int x = 0; x < 6; x++) {
		plays[x].setHand(deck.getCard(2,2*x), deck.getCard(2,2*x+1));
	    }
	    for (int y = 0; y < num - 6; y++) {
		plays[y+6].setHand(deck.getCard(3,2*y), deck.getCard(3,2*y+1));
	    }
	}
	else {
	    for (int z = 0; z < plays.length; z++) {
		plays[z].setHand(deck.getCard(2,2*z), deck.getCard(2,2*z+1));
	    }	    
	}
    }
    
    public Card retCard(int ctr){
	return river[ctr];
    }
    
    public Deck getDeck() {
	return deck;
    }
    
    public int countGams() {
	int num = 0;
	for (int x = 0; x < bets.length; x++) {
	    if (bets[x]) {
		num += 1;
	    }
	}
	return num;
    }

    public boolean isNotFolded(Player c) {
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

    public void call(Player a, int[] pot) {
	if (pot[0] >= a.getChips()) {
	    allIn(a, pot);
	    return;
	}
	pot[1] += pot[0];
	a.setChips(a.getChips() - pot[0]);
    }

    public void raise(Player a, int[] pot, int bet) {
	if (pot[0] >= a.getChips()) {
	    allIn(a, pot);
	    return;
	}
	pot[1] += bet;
	pot[0] = bet;
	a.setChips(a.getChips() - pot[0]);
    }

    public void allIn(Player a , int[] pot) {
	pot[1] += a.getChips();
	a.setChips(0);
	if (countGams() == 2) {
	    for (int x = 0; x < plays.length; x++) {
		if (isNotFolded(plays[x])) {
		    plays[x].setChips(plays[x].getChips() + main[0]);
		    break;
		}
	    }
	}
	else {
		side[0] += a.getChips();
		if (side[1] < a.getChips()) {
		    side[1] = a.getChips();
		}
		a.setChips(0);
	}
    }

    public void play() {
	setRiver();
	deal();
    }
    
    public static void main(String[] args) {
	Player niels = new Player();
	Player sham = new Player();
	Player anna = new Player();
	Table asher = new Table(new Player[]{niels, sham, anna});
	asher.setRiver();
	for (int i = 0; i < 5; i++) {
	    System.out.println(asher.retCard(i));
	}
	asher.deal();
	System.out.println("----------------niels---------------");
	for (int x = 0; x < 2; x++) {
	    System.out.println(niels.getHand()[x]);
	}
	System.out.println("----------------sham---------------");
	for (int x = 0; x < 2; x++) {
	    System.out.println(sham.getHand()[x]);
	}
	System.out.println("----------------anna---------------");
	for (int x = 0; x < 2; x++) {
	    System.out.println(anna.getHand()[x]);
	}
	System.out.println(asher.isNotFolded(niels));
	niels.setChips(100);
	asher.call(niels, asher.main);
	System.out.println(niels.getChips());
	asher.raise(niels, asher.main, 50);
	System.out.println(niels.getChips());
	asher.allIn(niels, asher.main);
	System.out.println(niels.getChips());	
	System.out.println("                          ");
	for (int x = 0; x < 2; x++) {
	    System.out.println(asher.main[x]);
	}
	asher.fold(niels);
	for (int x = 0; x < 2; x++) {
	    System.out.println(asher.side[x]);
	}	
	System.out.println(asher.isNotFolded(niels));
    }
}    
