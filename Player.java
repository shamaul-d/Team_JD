/*
  Niels Graham, Shamaul Dilmohamed 
  APCS1 pd10
  Final Project -- Texas Hold'em Poker
  2016-1-11
*/


public class Player{
    private Card[] hand = new Card[2]; 
    private int chips; //How much monies you have
    private Card[] fullHand = new Card[7]; //the river and Player's hand in one array
    private int handLevel;

    //=============User Functions==========================

    public String showHand(){
	String ans = "";
	for (int x = 0; x < 2; x++) {
	    ans += hand[x];
	    ans += "\n";
	}
	return ans;
    }

    //=============Get Functions==========================
    public Card[] getHand(){
	return hand;
    }

    public int getChips(){
	return chips;
    }

    public int getHandLevel(){
	return handLevel;
    }
    //=============Winnning Hand Calculation Helper Functions===========
    public static int[] toInt(String[] x){ //turns the card rank into ints
	int[] retInt = new int[x.length];
	for (int i = 0; i < x.length; i++){
	    if (x[i].equals("J"))
		retInt[i] = 11;
	    else if (x[i].equals("Q"))
		retInt[i] = 12;
	    else if (x[i].equals("K"))
		retInt[i] = 13;
	    else if (x[i].equals("A"))
		retInt[i] = 14;
	    else
		retInt[i] = Integer.parseInt(x[i]);
	}
	return retInt;
    }
	
    public int[] sortArray(int[] x) { //simple least to greatest int sort
	for (int z = x.length - 1; z > 0; z--) { 
	    for(int i =  0; i < x.length - 1; i++) {
		int temp = 0;
		if(x[i] > x[i+1]) {
		    temp = x[i];
		    x[i] = x[i+1];
		    x[i+1] = temp;
		}
	    }
	}
	return x;
    }

    public int[] cardToInt(Card[] x){ //creates a int[] with the values of the cards in Card[]
	String[] numValsString =  new String[7];
	int[] numValsInt = new int[7];
	for (int i = 0; i < 7; i++){ //Removes the number values of cards and adds them to numVal
	    numValsString[i] = x[i].getRank();
	}
	numValsInt = toInt(numValsString);
	numValsInt = sortArray(numValsInt);
	return numValsInt;
    }

    public String[] cardToSuit(Card[] x){
	String[] retString = new String[7];
	for (int i = 0; i < 7; i++) {
	    retString[i] = x[i].getSuit();
    }
	return retString;
    }

    public int threeOfAKindRetInt(Card[] x){ //returns the card number that there are three of 
	int[] cardVals = new int[7];
	int counter = 0;
	int retInt = 0;
	cardVals = cardToInt(x);
	for(int i = 0; i < 4; i++){
	    counter = 0; //reset counter
	    for (int j = i; j < cardVals.length; i++){
		if(cardVals[i] == cardVals[j])
		    counter++;
	    }
	    if (counter == 3){
		retInt = cardVals[i];
		break;
	    }
	}
	return retInt;
    }

    public int[] listRemove(int[] x, int y){
	int[] retArray = new int[4];
	int counter = 0;
	for(int i = 0; i < 7; i++){
	    if(x[i] != y){
		retArray[counter] = x[i];
		counter++;
	    }
	}
	return retArray;
    }

    //    public int fourArrayPair(int[] x){
    //	int retInt;
	
	
	

	
    //===========Winning Hand Calculation Function=======================
    public boolean isStraightFlush(Card[] x){
	if (isStraight(x) && isFlush(x))
	    return true;
	else
	    return false;
	    } 

    public boolean isStraight(Card[] x){ //broken
	boolean retBol = false;
	int[] numValsInt = new int[7];
	numValsInt = cardToInt(x);
	for (int i = 0; i < 2; i++){
	    if(numValsInt[i] == numValsInt[i+1]-1)
		if(numValsInt[i] == numValsInt[i+2]-2)
		    if(numValsInt[i] == numValsInt[i+3]-3)
			if(numValsInt[i] == numValsInt[i+4]-4)
			    if(numValsInt[i] == numValsInt[i+5]-5)
				retBol = true;
	}
	return retBol;
    }
    
    public boolean isFourOfAKind(Card[] x){
	boolean retBol = false;
	int[] cardVals = new int[7];
	int counter = 0;
	cardVals = cardToInt(x);
	for(int i = 0; i < 3; i++){
	    counter = 0; //reset counter
	    for (int j = i; j < cardVals.length; i++){
		if(cardVals[i] == cardVals[j])
		    counter++;
	    }
	    if (counter == 4){
		retBol = true;
		break;
	    }
	}
	return retBol;
    }

    public boolean isFlush(Card[] x){
	boolean retBol = false;
	String[] suits = cardToSuit(x);
	int counter = 0;
	for(int i = 0; i < 3; i++){
	    counter = 0; //reset counter
	    for (int j = i; j < suits.length; j++){
		if(suits[i].equals(suits[j]))
		    counter++;
	    }
	    if (counter >= 4){
		retBol = true;
		break;
	    }
	}
	return retBol;
	
    }
    
    public boolean isThreeOfAKind(Card[] x){
	boolean retBol = false;
	int[] cardVals = new int[7];
	int counter = 0;
	cardVals = cardToInt(x);
	for(int i = 0; i < 4; i++){
	    counter = 0; //reset counter
	    for (int j = i; j < cardVals.length; i++){
		if(cardVals[i] == cardVals[j])
		    counter++;
	    }
	    if (counter == 3){
		retBol = true;
		break;
	    }
	}
	return retBol;
    }

    
    public boolean isFullHouse(Card[] x){
	boolean retBol = false;
	int[] cardValue = cardToInt(x);
	int threepair = 0;
        if (isThreeOfAKind(x))
	    threepair = threeOfAKindRetInt(x);
	else
	    return false;
	int[] fourCard = listRemove(x, threepair); //returns a 4 int long array with the three pair removed
	}

    public boolean isOnePair(Card[] x){
	
    


    public static void main(String[] args) {
	String [] test = new String[] {"A", "K", "4"};
	System.out.println(toInt(test)); 
	Player me = new Player();
	Table a = new Table(new Player[] {me});
	me.hand = new Card[]{a.getDeck().getCard(0,5), a.getDeck().getCard(0,6)};
	System.out.println(me.hand);
	
	me.fullHand = new Card[]{me.hand[0], me.hand[1], a.retCard(0) , a.retCard(1) , a.retCard(2) , a.retCard(3) , a.retCard(4)};
	for (int i = 0; i < 7; i++) {
	    System.out.println(me.fullHand[i]);
	}
	for (int i = 0; i < 7; i++) {
	    System.out.println(me.cardToInt(me.fullHand)[i]);
	}
	while (! (me.isStraight(me.fullHand))) {
	    a.getDeck().shuffle();
	    me.hand = new Card[]{a.getDeck().getCard(1,0), a.getDeck().getCard(1,1)};
	me.fullHand = new Card[]{me.hand[0], me.hand[1], a.retCard(0) , a.retCard(1) , a.retCard(2) , a.retCard(3) , a.retCard(4)};
	}
	for (int i = 0; i < 7; i++) {
	    System.out.println(me.fullHand[i]);
	}
	
    }
}
