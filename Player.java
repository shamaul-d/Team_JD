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
    private int handLevel; //highest is best 

    //=============User Functions==========================

    public String showHand(){
	String ans = "";
	for (int x = 0; x < 2; x++) {
	    ans += hand[x];
	    ans += "\n";
	}
	return ans;
    }

    //=============Get/Set Functions==========================
    public Card[] getHand(){
	return hand;
    }

    public int getChips(){
	return chips;
    }

    public int getHandLevel(){
	return handLevel;
    }
    
    public void setChips(int va) {
	chips = va;
    }
    
    public void setHand(Card a, Card b) {
	hand[0] = a;
	hand[1] = b;
    }

    public void setHandLevel() {
    	if (isStraightFlush(fullHand))
	    handLevel = 8;
	if (isFourOfAKind(fullHand))
	    handLevel = 7;
	if (isFullHouse(fullHand))
	    handLevel = 6;
	if (isFlush(fullHand))
	    handLevel = 5;
	if (isStraight(fullHand))
	    handLevel = 4;
	if (isThreeOfAKind(fullHand))
	    handLevel = 3;
	if (isTwoPair(fullHand))
	    handLevel = 2;
	if (isOnePair(fullHand))
	    handLevel = 1;
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
	    for (int j = i; j < cardVals.length; j++){
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
	    
    public boolean fourArrayPair(int[] x){ //used in fullhouse 
	boolean retBol = false;
	for(int i = 0; i < 4; i++){
	    for(int j = i + 1; j < 4; j++){
		if(x[i] == x[j])
		    retBol = true;
	    }
	}
	return retBol;
    }

    public int isOnePairReturn(Card[] x){
	int retInt = 0;
	int[] cardValue = cardToInt(x);
	for(int i = 0; i < 6; i++){
	    for(int j = i + 1; j < 7; j++){
		if(cardValue[i] == cardValue[j])
		    retInt = cardValue[j];
	    }
	}
	return retInt;
    }
	    
    public boolean intIsOnePair(int[] x){//used in isTwoPair
	boolean retBol = false;
	for(int i = 0; i < x.length -1; i++){
	    for(int j = i + 1; j < x.length; j++){
		if(x[i] == x[j])
		    retBol = true;
	    }
	}
	return retBol;
    }
    
    public int[] removeDouble(int[] x){         
        int j = 0;
        int i = 1;
        while(i < x.length){
            if(x[i] == x[j]){
                i++;
            }else{
                x[++j] = x[i++];
            }   
        }
        int[] output = new int[j+1];
        for(int k=0; k<output.length; k++){
            output[k] = x[k];
        }
         
        return output;
    }

    public boolean aceSwitch(int[] x){//turns aces from 14 to 1
	boolean retBol =  false;
	for (int i = 0; i < x.length; i++){
	    if (x[i] == 14){
		x[i] = 1;
		retBol = true;
	    }
	}
	return retBol;
    }
	    
	    
		//===========Winning Hand Calculation Function=======================
    public boolean isStraightFlush(Card[] x){
	if (isStraight(x) && isFlush(x))
	    return true;
	else
	    return false;
    } 
    
    public boolean isStraight(Card[] x){ //broken
	boolean retBol = false;
	int[] numInt = new int[7];
	int counter = 0;
	numInt = cardToInt(x);
	int[] numValsInt = removeDouble(numInt);
	for (int i = 0; i < numValsInt.length - 2; i++){
	    if(numValsInt[i] == numValsInt[i+1]-1)
		if(numValsInt[i] == numValsInt[i+2]-2)
		    if(numValsInt[i] == numValsInt[i+3]-3)
			if(numValsInt[i] == numValsInt[i+4]-4)
			    if(numValsInt[i] == numValsInt[i+5]-5)
				retBol = true;
	}
	if (!retBol){
	    if (aceSwitch(numValsInt)){ //Checks with both ace values 
		for (int i = 0; i < numValsInt.length - 2; i++){
		    if(numValsInt[i] == numValsInt[i+1]-1)
			if(numValsInt[i] == numValsInt[i+2]-2)
			    if(numValsInt[i] == numValsInt[i+3]-3)
				if(numValsInt[i] == numValsInt[i+4]-4)
				    if(numValsInt[i] == numValsInt[i+5]-5)
					retBol = true;
		}
	    }
	}
	return retBol;
    }
    
    public boolean isFourOfAKind(Card[] x){
	boolean retBol = false;
	int[] cardVals = new int[7];
	int counter = 0;
	cardVals = cardToInt(x);
	for(int i = 0; i < 2; i++){
	    counter = 0; //reset counter
	    for (int j = i; j < cardVals.length; j++){
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
	    for (int j = i; j < cardVals.length; j++){
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
	int[] fourCard = listRemove(cardValue, threepair); //returns a 4 int long array with the three pair removed
	System.out.println(fourCard.length);
	if (fourArrayPair(fourCard))
	    retBol = true;
	return retBol;
	}

    public boolean isTwoPair(Card[] x){
	boolean retBol = false;
	int[] cardValue = cardToInt(x);
	if (isOnePair(x)){
	    int firstPair = isOnePairReturn(x);
	    int[] fiveCard = listRemove(cardValue, firstPair);
	    if (intIsOnePair(fiveCard))
		retBol = true;
	
	}
	return retBol;
    }

    public boolean isOnePair(Card[] x){
	boolean retBol = false;
	int[] cardValue = cardToInt(x);
	for(int i = 0; i < 7; i++){
	    for(int j = i; j < 7; j++){
		if(cardValue[i] == cardValue[j])
		    retBol = true;
	    }
	}
	return retBol;
    }

    //===============Card Return Functions==============
    public static Card highCard(Card[] x){
	Card retCard = x[0];
	for (int i = 0; i < x.length; i++){
	    if ( retCard.compareTo(x[i]) < 0)
		retCard = x[i];
	}
	return retCard;
    }
	
    
    public static void main(String[] args){
	Player me = new Player();
	Table a = new Table(new Player[] {me});
	me.hand = new Card[]{a.getDeck().getCard(0,5), a.getDeck().getCard(0,6)};
	System.out.println(me.hand);
	me.fullHand = new Card[]{me.hand[0], me.hand[1], a.retCard(0) , a.retCard(1) , a.retCard(2) , a.retCard(3) , a.retCard(4)};
	for (int i = 0; i < 7; i++) {
	    System.out.println(me.fullHand[i]);
	}
	// ^^ test setup mech, if want to mess with go to table 
	System.out.print(me.isFullHouse(me.fullHand));
	for (int i = 0; i < 7; i++) {
	    System.out.println(me.fullHand[i]);
	}
	System.out.println(highCard(me.fullHand));
    }
    
}

