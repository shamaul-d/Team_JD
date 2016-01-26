import cs1.Keyboard;
import java.util.ArrayList;
import java.util.Arrays;

public class Table {

    private int[] main; // holds current bet and size of pot

    private int[] side; // same qualities

    private boolean[] bets; // holds data if players are betting or not

    private int bet; // number of active people in the hand

    private boolean[] bankrupt; // if a player has lost all their funds

    private Card[] river; // holds the cards for the river
    
    private Deck deck; // holds a deck of cards
    
    private Player[] plays; // players in game

    public Table(Player[] gamblers){
	deck = new Deck();
	main = new int[2];
	main[0] = 5; //main[1] will get value of 0
	side = new int[2]; // both indices will get val of 0
	bets = new boolean[gamblers.length];
	for (int i = 0; i < bets.length; i++) {
	    bets[i] = true;
	}
	bankrupt = new boolean[gamblers.length];
	for (int i = 0; i < bankrupt.length; i++) {
	    bankrupt[i] = true;
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

    public boolean isNotBankrupt(Player c) {
	for (int x = 0; x < bankrupt.length; x++) {
	    if (c.toString().equals(plays[x].toString())) {
		return bankrupt[x];
	    }
	}
	return false;
    }

    public void fold(Player a) {
	for (int x = 0; x < plays.length; x++) {
	    if (a.toString().equals(plays[x].toString())) {
		bets[x] = false;
		bet++;
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

    public int[] raise(Player a, int[] pot, int bet) {
	if (pot[0] >= a.getChips()) {
	    pot = allIn(a, pot);
	    return pot;
	}
	pot[1] += bet;
	pot[0] += bet;
	a.setChips(a.getChips() - pot[0]);
	return pot;
    }

    public int[] allIn(Player a , int[] pot) {
	System.out.println("All in!");
	pot[1] += a.getChips();
	a.setChips(0);
	if (countGams() == 2) {
	    for (int x = 0; x < plays.length; x++) {
		if (isNotFolded(plays[x])) {
		    plays[x].setChips(plays[x].getChips() + main[0]);
		    break;
		}
	    }
	    return main;
	}
	else {
		side[0] += a.getChips();
		if (side[1] < a.getChips()) {
		    side[1] = a.getChips();
		}
		a.setChips(0);
		return side;
	}
    }

    public void win(Player p, int[] pot) {
	p.setChips(p.getChips() + pot[1]);
	pot[1] = 0;
	pot[0] = 5;
    }

    public void play(String[] names) {
	int[] pot = main;
	ArrayList<Player> remain = new ArrayList<Player>();
	bet = 0;
	setRiver();
	deal();
	for (int c = 0; c < 3; c++) {
	    for (int x = 1; x < plays.length + 1; x++) {
		if (isNotFolded(plays[x-1]) && isNotBankrupt(plays[x-1])) { 
		    if  (bet == plays.length-1) {
			System.out.println("Congratulations! Player " + x + "(" + names[x-1] + ") has won the main pot!");
			win(plays[x-1],main);
			return;
		    }
		    if (x == 1){
			System.out.println("\u001b[2J\u001b[H");
		    }
		}
		System.out.println("Current bet is " + pot[0] + ".");
		System.out.println("Current pot size is " + pot[1] + ".");	    		System.out.println(plays[x-1].showHand());
		System.out.print("Player " + x + "( " + names[x-1] + " ): " + "Would you like to call, raise, or fold? (c, r, or f): "); 
		playerFunctions(pot, x-1);
	    }
	    System.out.println("\u001b[2J\u001b[H");
	    System.out.println("Revealing card in river:");
	    if (c == 0) {
		System.out.println(retCard(0));
		System.out.println(retCard(1)); 
		System.out.println(retCard(2));
		pot[0] = 0;
	    }
	    else if (c == 1){
		System.out.println(retCard(0));
		System.out.println(retCard(1)); 
		System.out.println(retCard(2));
		System.out.println(retCard(3));
	    }
	    else {
		System.out.println(retCard(0));
		System.out.println(retCard(1)); 
		System.out.println(retCard(2));
		System.out.println(retCard(3));
		System.out.println(retCard(4));
	    }
	}
	for (int a = 0; a < plays.length; a++) {
	    if (isNotFolded(plays[a])) {
		remain.add(plays[a]);
	    }
	}
	for (int b = 0; b < remain.size(); b++) {
	    for (int m = remain.size()-1; m > 1; m--) {
		if (remain.get(m-1).compareTo(remain.get(m)) > 0) {
		    Player temp = remain.get(m-1);
		    remain.set(m-1, remain.get(m));
		    remain.set(m, temp);
		}
	    } 
	}
	if (Arrays.equals(pot,main)) {
	    for (int r = 1; r < plays.length + 1; r++) {
		if (remain.get(0) == plays[r-1]) {
		    System.out.println("Congratulations! Player "+ r + "(" + names[r-1] + ") has won the pot!");
		    win(plays[r-1],main);
		    break;
		}
	    }
	}
	else {
	    for (int q = 1; q < plays.length + 1; q++) {
		if ((remain.get(0) == plays[q-1]) && plays[q-1].getChips() ==0) {
		    System.out.println("Congratulations! Player "+ q + "(" + names[q-1] + ") has won the side pot!");
		    win(plays[q-1],side);
		    while (remain.get(q).getChips() != 0) {
			q++;
		    }
		    System.out.println("Congratulations! Player "+ q + "(" + names[q-1] + ") has won the main pot!");
		    win(plays[q-1],main);
		}
		else if (remain.get(0) == plays[q-1]) {
		    System.out.println("Congratulations! Player "+ q + "(" + names[q-1] + ") has won the main pot!");
		    win(plays[q-1],main);
		    while (remain.get(q).getChips() == 0) {
			q++;
		    }
		    System.out.println("Congratulations! Player "+ q + "(" + names[q-1] + ") has won the side pot!");
		    win(plays[q-1],side);
		}
	    }
	}
	for (int n = 1; n < plays.length + 1; n++) {
	    System.out.println("Player " + n + "(" + names[n-1] + ") had: ");
	    System.out.println(plays[n-1].showHand());
	}
	for (int d = 0; d < plays.length; d++) {
	    if (plays[d].getChips() == 0) {
		System.out.println("Sorry, you've gone bankrupt player " + (d+1) + "(" + names[d] + "). I'm afraid your time at this table is done.");
		bankrupt[d] = false;
	    }
	    bets[d] = true;
	}
    }




    
    public int[] raiseG(int[] pot, int x) {
	try {
	    int r = Integer.parseInt(Keyboard.readString());
	    if (r <= 0) {
		System.out.println("Be serious please.");
		pot = raiseG(pot,x);
		return pot;
	    }
	    else {
		if (pot.equals(main)) {
		    pot = raise(plays[x],main,r);
		}
		else {
		    pot = raise(plays[x],side,r);
		}
	    }
	}
	catch (Exception e) {
	    System.out.println("A number, please.");
	    pot = raiseG(pot,x);
	    return pot;
	}
	return pot;
    }

    public void playerFunctions(int[] pot, int x) {
	String ans = Keyboard.readString();
	if (ans.equals("c")) {
	    if (Arrays.equals(pot,main)) {
		call(plays[x],main);
		System.out.println("Calling bet of " + main[0] +"...");
	    }
	    else {
		call(plays[x],side);
		System.out.println("Calling bet of " + side[0] +"...");
	    }
	}
	else if (ans.equals("r")) {
	    System.out.println("By how much?");
	    pot = raiseG(pot,x);
	    if (Arrays.equals(pot, main)) {
		System.out.println("Raising bet to bet of " + main[0] +"...");	    }
	    else {
		System.out.println("Raising bet to bet of " + side[0] +"...");	  	    }
	}
	else if (ans.equals("f")) {
	    fold(plays[x]);
	    System.out.println("Folding...");	  	
	}
	else {
	    System.out.println("Sorry, we didn't get that.");
	    playerFunctions(pot, x);
	    return;
	}
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
