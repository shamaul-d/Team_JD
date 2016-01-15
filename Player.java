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
    public int[] toInt(String[] x){ //turns the card rank into ints
	int[] retInt = new int[x.length];
	for (int i = 0; i < x.length; i++){
	    if (x[i].equals("J"))
		retInt[i] = 11;
	    else if (x[i].equals("Q"))
		retInt[i] = 12;
	    else if (x[i].equals("k"))
		retInt[i] = 13;
	    else if (x[i].equals("A"))
		retInt[i] = 14;
	    else
		retInt[i] = Integer.parseInt(x[i]);
	}
	return retInt;
    }
	
    public void sortArray(int[] x) { //simple least to greatest int sort
	for(int i=1; i<x.length; i++) {
	    int temp=0;
	    if(x[i-1] > x[i]) {
		temp = x[i-1];
		x[i-1] = x[i];
		x[i] = temp;
	    }
	}
    }

    public int[] cardToInt(Card[] x){
	String[] numValsString =  new String[7];
	int[] numValsInt = new int[7];
	for (int i = 0; i < 7; i++){ //Removes the number values of cards and adds them to numVal
	    numValsString[i] = x[i].getRank();
	}
	numValsInt = toInt(numValsString);
	sortArray(numValsInt);
	return numValsInt;
    }
    //===========Winning Hand Calculation Function=======================
    public boolean isStraight(Card[] x){
	boolean retBol = false;
	int[] numValsInt = new int[7];
	numValsInt = cardToInt(x);
	for (int i = 0; i < 3; i++){
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
    
}
