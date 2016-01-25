// driver for poker game

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import cs1.Keyboard;

public class Poker {
    
    public static void start() {
	String response = Keyboard.readString();
	if (response.equals("y")) {
	    System.out.println("\nOkay, here you go! \n");
	    // file which contains rules of poker displayed here
	    try {
		FileInputStream st = new FileInputStream("Pokerrules.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(st));
		String line = "";
		while ((line = br.readLine()) != null) {
		    System.out.println(line);
		}
	    }	    
	    catch (Exception e) {
		System.out.println("Sorry, we couldn't find the file.");
	    }
	}
	else if (response.equals("n")) {
	    System.out.println("\nAwesome, on to the game! \n");
	}
	else {
	    System.out.print("\nSorry we didn't get that, please try again. (type y or n): ");
	    start();
	}
    }    

    public static String[] setup() {
	System.out.print("How many players want to play? (2-12): ");
	String numS = Keyboard.readString();
	int num = 0;
	String[] na; 
	try {
	    num = Integer.parseInt(numS);
	    if (num <= 0) {
		System.out.println("We're pretty sure the number of people playing has to be positive...");
		setup();
	    }
	    if (num == 1) {
		System.out.println("You can't play poker with only one player. That's kinda sad.");
		setup();
	    }
	    
	    else if (num > 12) {
		System.out.println("That's too many players. We refuse.");
		setup();
	    }
	    else {
		na = new String[num];
		for (int x = 1; x < num + 1; x++) {
		    System.out.println("Name of player "+ x + " :");
		    String name = Keyboard.readString();
		    na[x-1] = name;
		}
		return na;
	    }
	}
	catch (Exception e) {
	    System.out.println("A number would be nice, please.");
	    setup();
	}
	return new String[1]; //just in case
    }
	
    public static Player[] choice(String[] names) {
	Player[] pl = new Player[names.length];
	String ch = Keyboard.readString();
	if (ch.equals("d")) {
	    for (int x = 0; x < names.length; x++) {
		pl[x] = new Player();
	    }
	}
	else if (ch.equals("c")) { 
	    pl = startingChips(names);
	}
	else {
	    System.out.print("Wouldn't you like to start our beautiful game as quickly as possible? It's pretty good, if we say so ourselves. (type d or c) : ");
	    choice(names);
	}
	return pl;
    }
    
    public static Player[] startingChips(String[] names) {
	String n;
	int c;
	Player[] pl = new Player[names.length];
	System.out.println("How many chips would you like?");
	n = Keyboard.readString();
	try {
	    c = Integer.parseInt(n);
	    if (c <= 0) {
		System.out.println("We're pretty sure the number of chips has to be positive...");
		pl = startingChips(names);
	    }
	    else {
		for (int x = 0; x < names.length; x++) {
		    pl[x] = new Player(c);
		}
	    }
	}
	catch (Exception e) {
	    System.out.println("Chip values are to be taken seriously.");
	    pl = startingChips(names);
	}
	return pl;
    }
   
    public static void main(String[] args) {
	System.out.print("Welcome to Texas Hold'em Poker! Would you like a list of rules before we begin, or are you ready to go? (type y or n): ");
	start();
	String[] names = setup();
	System.out.println("Would you like to start with the default number of chips, or a custom amount? (default is 100) (type d or c): ");
	Player[] pl = choice(names);
	Table lv = new Table(pl);
    }
}
