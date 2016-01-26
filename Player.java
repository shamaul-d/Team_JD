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

    public Player() {
        setChips(100);
    }

    public Player(int ch) {
        setChips(ch);
    }

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

    public Card[] getFullHand(){
	return fullHand;
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


    public void setFullHand(Card[] hand, Card[] riv) {
        for (int x = 0; x < 2; x++) {
            fullHand[x] = hand[x];
        }
        for (int y = 0; y < 5; y++) {
            fullHand[y+2] = riv[y];
        }
    }

    public void setHandLevel() {
        if (isStraightFlush(fullHand))
            handLevel = 8;
        else if (isFourOfAKind(fullHand))
            handLevel = 7;
        else if (isFullHouse(fullHand))
            handLevel = 6;
        else if (isFlush(fullHand))
            handLevel = 5;
        else if (isStraight(fullHand))
            handLevel = 4;
        else if (isThreeOfAKind(fullHand))
            handLevel = 3;
        else if (isTwoPair(fullHand))
            handLevel = 2;
        else if (isOnePair(fullHand))
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
        for(int i = 0; counter != 4; i++){
            if(x[i] != y){
                retArray[counter] = x[i];
                counter++;
            }
        }
        return retArray;
    }

    public int[] listRemoveOnePair(int[] x, int y){
        int[] retArray = new int[5];
        int counter = 0;
        for(int i = 0; counter != 5; i++){
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
        for(int i = 0; i < x.length -1 ; i++){
            for(int j = i + 1; j < x.length; j++){
                if(cardValue[i] == cardValue[j])
                    retInt = cardValue[j];
            }
        }
        return retInt;
    }

    public int isOnePairReturnInt(int[] x){
	int retInt = 0;
	for(int i = 0; i < x.length -1 ; i++){
            for(int j = i + 1; j < x.length; j++){
                if(x[i] == x[j])
                    retInt = x[j];
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

    public boolean isOnePairInt(int[] x){
	boolean retBol = false;
        for (int i = 0; i < x.length; i++)
	    for (int j = i + 1; j < x.length; j++)
		if(x[i] == x[j]){
		    retBol = true;
		    break;
		}
	return retBol;
    }
    public int getOnePairInt(int[] x){
	int retInt = -1;
        for (int i = 0; i < x.length; i++)
	    for (int j = i + 1; j < x.length; j++)
		if(x[i] == x[j]){
		    retInt = x[i];
		    break;
		}
	return retInt;
    }
	
    
    public int[] isStraightCondence(Card[] x){
	int[] numInt = new int[7];
	numInt = cardToInt(x);
	if (isOnePair(x)){
	    int[] numValsInt1 = removeDouble(numInt);
	    if (isOnePairInt(numValsInt1)){
		int[] numValsInt2 = removeDouble(numValsInt1);
		if (isOnePairInt(numValsInt2)){
		    int[] z = {-1};
		    return z; //array will be less than 4 cards long and cannot be a straight
		}
		return numValsInt2;
	    }
	    return numValsInt1;
	}
	return numInt;
    }
	
	


                //===========Winning Hand Calculation Function=======================
    public boolean isStraightFlush(Card[] x){
        if (isStraight(x) && isFlush(x))
            return true;
        else
            return false;
    }

    public boolean isStraight(Card[] x){
        boolean retBol = false;
        int counter = 0;
	int[] numValsInt = isStraightCondence(x);
	if (numValsInt.length >= 5){
	    for (int i = 0; i < numValsInt.length - 5; i++){
		if(numValsInt[i] == numValsInt[i+1]-1){
		    if(numValsInt[i] == numValsInt[i+2]-2){
			if(numValsInt[i] == numValsInt[i+3]-3){
			    if(numValsInt[i] == numValsInt[i+4]-4){
				if(numValsInt[i] == numValsInt[i+5]-5){
				    retBol = true;
				}
			    }
			}
		    }
		}
	    }
	    if (!retBol){
		if (aceSwitch(numValsInt)){ //Checks with both ace values
		    for (int i = 0; i < numValsInt.length - 5; i++){
			if(numValsInt[i] == numValsInt[i+1]-1){
			    if(numValsInt[i] == numValsInt[i+2]-2){
				if(numValsInt[i] == numValsInt[i+3]-3){
				    if(numValsInt[i] == numValsInt[i+4]-4){
					if(numValsInt[i] == numValsInt[i+5]-5){
					    retBol = true;					    
					}
				    }
				}
			    }
			}
		    }
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
            int[] fiveCard = listRemoveOnePair(cardValue, firstPair);
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
    public  Card highCard(Card[] x){
        Card retCard = x[0];
        for (int i = 0; i < x.length; i++){
            if ( retCard.compareTo(x[i]) < 0)
                retCard = x[i];
        }
        return retCard;
    }

    public int getOnePair(Card[] x){
        int retInt = -1;
        int[] cardValue = cardToInt(x);
        for(int i = 0; i < 7; i++){
            for(int j = i; j < 7; j++){
                if(cardValue[i] == cardValue[j])
                    retInt = cardValue[i];
            }
        }
        return retInt;
    }
    public int[] twoPairGet(Card[] x){
	int[] retArray = new int[2];
	int[] cardValue = cardToInt(x);
	retArray[0]  = isOnePairReturn(x);
	int[] fiveCard = listRemove(cardValue, retArray[0]);
	retArray[1] = isOnePairReturnInt(fiveCard);
	return retArray;
    }

    public int[] getStraight(Card[] x){
	boolean retBol = false;
	int[] retArray = new int[5];
        int[] numInt = new int[7];
        numInt = cardToInt(x);
        int[] numValsInt = removeDouble(numInt);
        for (int i = 0; i < numValsInt.length - 2; i++){
            if(numValsInt[i] == numValsInt[i+1]-1)
                if(numValsInt[i] == numValsInt[i+2]-2)
                    if(numValsInt[i] == numValsInt[i+3]-3)
                        if(numValsInt[i] == numValsInt[i+4]-4)
                            if(numValsInt[i] == numValsInt[i+5]-5){
                                retArray[0] = numValsInt[i];
				retArray[1] = numValsInt[i+1];
				retArray[2] = numValsInt[i+2];
				retArray[3] = numValsInt[i+3];
				retArray[4] = numValsInt[i+4];
				retBol = true;
			    }
        }
        if (!retBol){
            if (aceSwitch(numValsInt)){ //Checks with both ace values
                for (int i = 0; i < numValsInt.length - 2; i++){
                    if(numValsInt[i] == numValsInt[i+1]-1)
                        if(numValsInt[i] == numValsInt[i+2]-2)
                            if(numValsInt[i] == numValsInt[i+3]-3)
                                if(numValsInt[i] == numValsInt[i+4]-4)
                                    if(numValsInt[i] == numValsInt[i+5]-5){
					retArray[0] = numValsInt[i];
					retArray[1] = numValsInt[i+1];
					retArray[2] = numValsInt[i+2];
					retArray[3] = numValsInt[i+3];
					retArray[4] = numValsInt[i+4];
				    }
                                        
                }
            }
        }
        return retArray;
    }

    public int suitStringToInt(String x){
	int retInt = -1;
	if (x.equals("diamond"))
	    retInt = 1;
	else if (x.equals("club"))
	    retInt = 2;
	else if (x.equals("heart"))
	    retInt = 3;
	else if (x.equals("spade"))
	    retInt = 4;
	return retInt;
    }
	
	    
	
    
    public int getFlushInt(Card[] x){
        int retInt = -1;
        String[] suits = cardToSuit(x);
        int counter = 0;
        for(int i = 0; i < 3; i++){
            counter = 0; //reset counter
            for (int j = i; j < suits.length; j++){
                if(suits[i].equals(suits[j]))
                    counter++;
            }
            if (counter >= 4){
                retInt = suitStringToInt(suits[i]);
                break;
            }
        }
        return retInt;

    }

    public int getFourOfAKindCard(Card[] x){
        int retInt = -1;
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
		retInt = cardVals[i];
		break;
            }
        }
        return retInt;
    }




    //===============CompareTo Functions================


        public int compareTo(Player a){
            int retInt = 0;
	    a.setHandLevel();
	    this.setHandLevel();
            if (this.getHandLevel() > a.getHandLevel())
                retInt = 1;
            else if (this.getHandLevel() < a.getHandLevel())
                retInt = -1;
            else{
		if (this.getHandLevel() == 0){
		    Card highCardA = highCard(this.getFullHand());
		    Card highCardB = highCard(a.getFullHand());
		    if (highCardA.compareTo(highCardB) == 1)
			retInt = 1;
		    else if (highCardA.compareTo(highCardB) == -1)
			retInt = -1;
		}		            	    
		else if (this.getHandLevel() == 1){
		    if (getOnePair(this.getFullHand()) > getOnePair(a.getFullHand()))
			retInt = 1;
		    else if(getOnePair(this.getFullHand()) > getOnePair(a.getFullHand()))
			retInt = -1;
		    else{
			if (highCard(this.getFullHand()).compareTo(highCard(a.getFullHand())) == 1)
			    retInt = 1;
			else
			    retInt = -1;
		    }
		}
		else if (this.getHandLevel() == 2){
		    int[] twoPairHandA = sortArray(twoPairGet(this.getFullHand()));
		    int[] twoPairHandB = sortArray(twoPairGet(a.getFullHand()));
		    if (twoPairHandA[1] > twoPairHandB[1])
			retInt = 1;
		    else if (twoPairHandB[1] > twoPairHandA[1])
			retInt = -1;
		    else if (twoPairHandB[1] == twoPairHandA[1]){
			if (twoPairHandA[0] > twoPairHandB[0])
			    retInt = -1;
			else
			    retInt = 1;
		    }
		}
		else if (this.getHandLevel() == 3){
		    if(getOnePair(this.getFullHand()) > getOnePair(a.getFullHand()))
			retInt = 1;
		    else if(getOnePair(this.getFullHand()) < getOnePair(a.getFullHand()))
			retInt = -1;
		    else if(getOnePair(this.getFullHand()) == getOnePair(a.getFullHand()))
			retInt = 0;
		}
		else if (this.getHandLevel() == 4){
		    int[] straightA = getStraight(this.getFullHand());
		    int[] straightB = getStraight(a.getFullHand());
		    if (straightA[4] > straightB[4])
			retInt = 1;
		    else if(straightB[4] > straightA[4])
			retInt = -1;
		    else
			retInt = 0;
		}
		else if (this.getHandLevel() == 5){
		    if (getFlushInt(this.getFullHand()) > getFlushInt(a.getFullHand()))
			retInt = 1;
		    else if (getFlushInt(this.getFullHand()) < getFlushInt(a.getFullHand()))
			retInt = -1;
		    else
			retInt = 0;
				
		}
		else if (this.getHandLevel() == 6){
		    int[] fullHouseIntA = cardToInt(this.getFullHand());
		    int[] fullHouseIntB = cardToInt(a.getFullHand());
		    if (threeOfAKindRetInt(this.getFullHand()) > threeOfAKindRetInt(a.getFullHand()))
			retInt = 1;
		    else if (threeOfAKindRetInt(this.getFullHand()) < threeOfAKindRetInt(a.getFullHand()))
			retInt = -1;
		    else
			retInt = 0;
		}
		else if (this.getHandLevel() == 7){
		    if (getFourOfAKindCard(this.getFullHand()) > getFourOfAKindCard(a.getFullHand()))
			retInt = 1;
		    else if (getFourOfAKindCard(this.getFullHand()) < getFourOfAKindCard(a.getFullHand()))
			retInt = -1;
		    else
			retInt = 0;
		}
		else if (this.getHandLevel() == 8){
		    int[] straightIntA = getStraight(this.getFullHand());
		    int[] straightIntB = getStraight(a.getFullHand());
		    if (straightIntA[4] > straightIntB[4])
			retInt = 1;
		    if (straightIntA[4] < straightIntB[4])
			retInt = -1;
		    else
			retInt = 0;
		}

	    }
	    return retInt;   
	}






    public static void main(String[] args){
        Player me = new Player();
	Player hi = new Player();
        Table a = new Table(new Player[] {me, hi});
	a.setRiver();
        me.hand = new Card[]{a.getDeck().getCard(0,5), a.getDeck().getCard(0,6)};
	hi.hand = new Card[]{a.getDeck().getCard(3,9), a.getDeck().getCard(2,12)};
	
        me.fullHand = new Card[]{me.hand[0], me.hand[1], a.retCard(0) , a.retCard(1) , a.retCard(2) , a.retCard(3) , a.retCard(4)};
	hi.fullHand = new Card[]{hi.hand[0], hi.hand[1], a.retCard(0) , a.retCard(1) , a.retCard(2) , a.retCard(3) , a.retCard(4)};

        for (int i = 0; i < 7; i++) {
            System.out.println(me.fullHand[i]);
        }
	System.out.println("second hand");
	for (int i = 0; i < 7; i++) {
            System.out.println(hi.fullHand[i]);
        }
	System.out.println(hi.getHandLevel());
	System.out.println(me.getHandLevel());
	System.out.println(hi.compareTo(me));
    }
    

}
