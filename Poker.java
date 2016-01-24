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
    
    public static void main(String[] args) {
	System.out.print("Welcome to Texas Hold'em Poker! Would you like a list of rules before we begin, or are you ready to go? (type y or n): ");
	start();
    }
}
