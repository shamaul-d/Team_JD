// driver for poker game

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import cs1.Keyboard;

public class Poker {
    static Player[] pl;
    static String[] names;
    
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

    public static void setup() {
	System.out.print("How many players want to play? (1-12): ");
	String numS = Keyboard.readString();
	int num = 0;
	try {
	    num = Integer.parseInt(numS);
	    if (num < 0) {
		System.out.println("We're pretty sure the number of people playing has to be positive...");
		setup();
		return;
	    }
	    else {
		names = new String[num];
		pl = new Player[num];
		for (int x = 1; x < num + 1; x++) {
		    System.out.println("Name of player "+ x + " :");
		    String name = Keyboard.readString();
		    names[x-1] = name;
		}
	    }
	}
	catch (Exception e) {
	    System.out.println("A number would be nice, please.");
	    setup();
	    return;
	}
    }
	
    public static void choice() {
	String ch = Keyboard.readString();
	if (ch.equals("d")) {
	    for (int x = 0; x < names.length; x++) {
		pl[x] = new Player();
	    }
	}
	else if (ch.equals("c")) {
	    System.out.println("How many chips would you like?");
	    for (int x = 0; x < names.length; x++) {
		pl[x] = new Player();
	    }
	}
	else {
	    System.out.println("Wouldn't you like to start our beautiful game as quickly as possible? It's pretty good, if we say so ourselves.");
	    choice();
	}
    }
  
    public static void main(String[] args) {
	System.out.print("Welcome to Texas Hold'em Poker! Would you like a list of rules before we begin, or are you ready to go? (type y or n): ");
	start();
	setup();
	System.out.println("Would you like to start with the default number of chips, or a custom amount? (default is 100) (type d or c): ");
	//choice();
    }
}
