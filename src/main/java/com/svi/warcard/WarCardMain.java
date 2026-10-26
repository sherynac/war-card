package com.svi.warcard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WarCardMain {
    public static void main(String[] args) {
        boolean willPlayAgain = true;
        Scanner kbd = new Scanner(System.in);

        while(willPlayAgain) {
            Game game = new Game(kbd);
            game.run();
            willPlayAgain = playAgain(kbd);
        }
        kbd.close();
    }

    public static boolean playAgain(Scanner kbd){
        boolean isValid = false;
        boolean willPlayAgain = false;

        while(!isValid){
            System.out.print("\nPlay Again (y/n): ");
            String input = kbd.nextLine().trim();
            if (input.equalsIgnoreCase("y")) {
                isValid = true;
                willPlayAgain = true;
            } else if (input.equalsIgnoreCase("n")){
                isValid = true;
                System.out.println("\n!! Thank you for playing War Card !!");
            } else {
                System.out.println("\nMake sure entered value is only either 'y' or 'n'");
            }
        }
        return willPlayAgain;
    }
}