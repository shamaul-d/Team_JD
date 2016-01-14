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
	return fullHand;
    }
    //=============Winnning Hand Calculation Helper Functions===========
    public int[] toInt(String[] x){
	int[] retInt = new int[x.lenght];
	for (int i = 0; i < x.length; i++){
	    if (x[i].equals("J")
	
    public void sortArray(int[] x) { //used in isStraight
	for(int i=1; i<x.length; i++) {
	    int temp=0;
	    if(x[i-1] > x[i]) {
		temp = x[i-1];
		x[i-1] = x[i];
		x[i] = temp;
	    }
	}
    }
    //===========Winning Hand Calculation Function=======================
    public boolean isStraight(Card[] x){
        String[] numValString =  new String[7];
	int[] numValsInt = new int[7];
	for (int i = 0; i < 7; i++){ //Removes the number values of cards and adds them to numVal
	    numValsString[i] = x[i].getRank();
	}
	numVals = sortArray(numVals);
	(1,4,5,6,7,8,k)
    }
